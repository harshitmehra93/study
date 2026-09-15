package study.lld.projects.counter;

import java.time.Instant;

public class TimedCounterTesterDecorator extends CounterTesterDecorator {
    public TimedCounterTesterDecorator(CounterTester counterTester) {
        super(counterTester);
    }

    @Override
    public void incrementOneMillionConcurrently(Counter counter) {
        Instant before = Instant.now();
        counterTester.incrementOneMillionConcurrently(counter);
        Instant after = Instant.now();
        System.out.println(
                "Time to run the test = " + (after.toEpochMilli() - before.toEpochMilli()) + "ms");
    }
}
