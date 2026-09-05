package study.ocp.concurrency.executors;

import java.util.concurrent.*;

/*
class JobExecutor {
    private ExecutorService executor;

    JobExecutor(int workers) { ... }

    Future<Integer> submit(Callable<Integer> job) { ... }

    void shutdown() { ... }
}
requirements -
configurable number of workers
submit jobs
return results using Future
gracefully shut down
wait maximum 5 seconds
if not terminated after 5 seconds, attempt forced shutdown

 */
public class MiniWorkerService {
    public static void main(String[] args) {
        JobExecutor executor = new JobExecutor(5);
        Callable<Integer> job = () -> 1;
        try {
            executor.submit(job);
            executor.submit(job);
            executor.submit(job);
            executor.submit(job);
            executor.submit(job);
        } finally {
            try {
                executor.shutdown();
            } catch (Exception e) {
            }
        }
    }
}

class JobExecutor {
    private final ExecutorService executor;

    JobExecutor(int workers) {
        executor = Executors.newFixedThreadPool(workers);
    }

    Future<Integer> submit(Callable<Integer> job) {
        return executor.submit(job);
    }

    void shutdown() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
