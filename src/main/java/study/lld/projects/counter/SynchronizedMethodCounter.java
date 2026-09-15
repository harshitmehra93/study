package study.lld.projects.counter;

public class SynchronizedMethodCounter implements Counter {

    private static final SynchronizedMethodCounter INSTANCE = new SynchronizedMethodCounter();
    ;

    static int counter = 0;

    private SynchronizedMethodCounter() {}

    public static SynchronizedMethodCounter getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized int get() {
        return counter;
    }

    @Override
    public synchronized void increment() {
        counter++;
    }
}
