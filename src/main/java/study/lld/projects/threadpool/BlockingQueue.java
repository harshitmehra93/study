package study.lld.projects.threadpool;

public interface BlockingQueue {
    void enqueue(Job<?> job);

    Job<?> dequeue() throws InterruptedException;

    int getSize();
}
