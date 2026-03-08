import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {

    private static final int MIN_SLEEP_MS = 25;
    private static final int MAX_SLEEP_MS = 250;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 999;

    private volatile boolean running = true;

    public Producer() {
        super("producer-thread");
    }

    public void shutdown() {
        running = false;
        interrupt();
    }

    private void produce() throws InterruptedException {
        int producedNumber = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER + 1);
        int sleepTime = ThreadLocalRandom.current().nextInt(MIN_SLEEP_MS, MAX_SLEEP_MS + 1);

        Thread.sleep(sleepTime);
        Buffer.put(producedNumber);
        System.out.println("Productor: Número " + producedNumber + " producido. Tamaño actual: " + Buffer.size());
    }

    @Override
    public void run() {
        while (running) {
            try {
                produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Productor detenido.");
    }
}
