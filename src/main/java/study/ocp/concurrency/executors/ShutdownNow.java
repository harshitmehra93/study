package study.ocp.concurrency.executors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
2 tasks probably running
remaining tasks queued

shutdownNow()
    ↓
running tasks get interruption request
queued/not-started tasks returned
 */
public class ShutdownNow {
    public static void main(String[] args) throws Exception {
        Runnable r =
                () -> {
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executor.submit(r);
        }

        List<Runnable> pending = executor.shutdownNow();
        System.out.println(pending.size());
    }
}
