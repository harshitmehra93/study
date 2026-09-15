package study.lld.projects.threadpool;

public class SimpleWorkerThread extends WorkerThread {

    private final BlockingQueue q;
    public volatile boolean shutdown = false;
    private final ThreadPoolMetrics metrics;

    public SimpleWorkerThread(BlockingQueue q, ThreadPoolMetrics metrics) {
        this.q = q;
        this.metrics = metrics;
    }

    @Override
    public void run() {
        while (!shutdown) {
            try {
                tryWorking();
            } catch (InterruptedException e) {
                if (shutdown) {
                    break;
                }
            }
        }
    }

    @Override
    public void tryWorking() throws InterruptedException {
        System.out.println(this.getName() + " checking work queue");
        Job job = q.dequeue();
        System.out.println(this.getName() + " started working on task");
        job.execute();
        metrics.incrementCompleted();
        System.out.println(this.getName() + " finished working on task");
    }

    @Override
    public void shutdown() {
        shutdown = true;
    }
}
