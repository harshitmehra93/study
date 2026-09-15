package study.lld.projects.threadpool;

public abstract class WorkerThread extends Thread {
    public abstract void tryWorking() throws InterruptedException;

    public abstract void shutdown();

    //    public abstract boolean isProcessingTask();
    //    public abstract ReentrantLock getLock();
}
