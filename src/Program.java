public class Program {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            producer.shutdown();
            consumer.shutdown();
            waitForThread(producer);
            waitForThread(consumer);
        }));

        producer.start();
        consumer.start();
    }

    private static void waitForThread(Thread thread) {
        try {
            thread.join(1_000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
