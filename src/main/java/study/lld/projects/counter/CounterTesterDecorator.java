package study.lld.projects.counter;

public abstract class CounterTesterDecorator implements CounterTester {
    protected final CounterTester counterTester;

    public CounterTesterDecorator(CounterTester counterTester) {
        this.counterTester = counterTester;
    }

    @Override
    public abstract void incrementOneMillionConcurrently(Counter counter);
}
