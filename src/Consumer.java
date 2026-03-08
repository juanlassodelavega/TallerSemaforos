import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {

    private static final int MIN_SLEEP_MS = 25;
    private static final int MAX_SLEEP_MS = 250;

    private volatile boolean running = true;

    public Consumer() {
        super("consumer-thread");
    }

    public void shutdown() {
        running = false;
        interrupt();
    }

    private void consume() throws InterruptedException {
        int sleepTime = ThreadLocalRandom.current().nextInt(MIN_SLEEP_MS, MAX_SLEEP_MS + 1);

        Thread.sleep(sleepTime);
        int consumedNumber = Buffer.take();
        System.out.println("Consumidor: Número " + consumedNumber + " consumido. Tamaño actual: " + Buffer.size());
    }

    @Override
    public void run() {
        while (running) {
            try {
                consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Consumidor detenido.");
    }
}
