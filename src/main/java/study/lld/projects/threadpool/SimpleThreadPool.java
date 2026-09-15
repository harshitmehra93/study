package study.lld.projects.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class SimpleThreadPool implements ThreadPool {

    private final int threadCount;
    private final List<WorkerThread> threads;
    private final BlockingQueue q;

    private final ThreadPoolMetrics metrics;

    public SimpleThreadPool(int threadCount, ThreadPoolMetrics metrics) {
        this.threadCount = threadCount;
        this.metrics = metrics;
        this.q = new SimpleBlockingQueue();
        threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            SimpleWorkerThread thread = new SimpleWorkerThread(this.q, metrics);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public <T> CompletableFuture<T> submit(Callable<T> c) {
        CompletableFuture<T> future = new CompletableFuture<>();
        q.enqueue(new Job<T>(future, c));
        metrics.incrementSubmitted();
        return future;
    }

    @Override
    public void shutdown() {
        threads.forEach(
                t -> {
                    t.shutdown();
                    t.interrupt();
                });
    }
}
