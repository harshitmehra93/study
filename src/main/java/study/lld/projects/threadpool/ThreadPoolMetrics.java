package study.lld.projects.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolMetrics {
    private AtomicInteger tasksSubmitted = new AtomicInteger(0);
    private AtomicInteger tasksCompleted = new AtomicInteger(0);

    public int getTasksSubmitted() {
        return tasksSubmitted.get();
    }

    public int getTasksCompleted() {
        return tasksCompleted.get();
    }

    public int incrementSubmitted() {
        return tasksSubmitted.getAndIncrement();
    }

    public int incrementCompleted() {
        return tasksCompleted.getAndIncrement();
    }
}
