package study.lld.projects.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
    private static AtomicCounter INSTANCE;
    private static AtomicInteger counter = new AtomicInteger(0);

    private AtomicCounter() {}

    static AtomicCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AtomicCounter();
        }
        return INSTANCE;
    }

    @Override
    public int get() {
        return counter.get();
    }

    @Override
    public void increment() {
        counter.getAndIncrement();
    }
}
