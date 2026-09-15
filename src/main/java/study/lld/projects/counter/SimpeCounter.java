package study.lld.projects.counter;

public class SimpeCounter implements Counter {
    private static SimpeCounter INSTANCE;
    private static int counter = 0;

    private SimpeCounter() {}

    static SimpeCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpeCounter();
        }
        return INSTANCE;
    }

    @Override
    public int get() {
        return counter;
    }

    @Override
    public void increment() {
        counter++;
    }
}
