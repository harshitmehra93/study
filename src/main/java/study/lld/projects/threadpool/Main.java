package study.lld.projects.threadpool;

import static java.lang.Thread.sleep;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
Learnings
1. CompletableFuture
2. ConditionalVariables await(), signal(), signalAll()
2. Blocking Queue

 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolMetrics metrics = new ThreadPoolMetrics();
        ThreadPool threadPool = new SimpleThreadPool(50, metrics);

        Instant before = Instant.now();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            futures.add(
                    threadPool.submit(
                            () -> {
                                sleep(1_000);
                                return 1;
                            }));
        }

        Instant after = Instant.now();
        System.out.println(
                "time elapsed to submit = "
                        + (after.toEpochMilli() - before.toEpochMilli())
                        + "ms");

        futures.forEach(
                f -> {
                    try {
                        System.out.println(f.get());
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println("unknown problem");
                    }
                });
        threadPool.shutdown();
        Instant end = Instant.now();
        System.out.println(
                "Total time elapsed = " + (end.toEpochMilli() - before.toEpochMilli()) + "ms");

        System.out.println("Tasks Submitted " + metrics.getTasksSubmitted());
        System.out.println("Tasks Completed " + metrics.getTasksCompleted());
    }
}
