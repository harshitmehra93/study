package study.lld.projects.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCounterTester implements CounterTester {
    @Override
    public void incrementOneMillionConcurrently(Counter counter) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        Runnable increment = () -> counter.increment();
        for (int i = 0; i < 1_000_000; i++) {
            service.execute(increment);
        }
        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
