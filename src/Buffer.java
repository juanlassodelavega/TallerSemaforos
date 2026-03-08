import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class Buffer {

    public static final int BUFFER_SIZE = 5;
    private static final BlockingQueue<Integer> STORE = new ArrayBlockingQueue<>(BUFFER_SIZE, true);

    private Buffer() {
    }

    public static void put(int value) throws InterruptedException {
        STORE.put(value);
    }

    public static int take() throws InterruptedException {
        return STORE.take();
    }

    public static int size() {
        return STORE.size();
    }

    public static boolean isEmpty() {
        return STORE.isEmpty();
    }

    public static int remainingCapacity() {
        return STORE.remainingCapacity();
    }
}
