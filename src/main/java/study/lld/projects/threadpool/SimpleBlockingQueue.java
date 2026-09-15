package study.lld.projects.threadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleBlockingQueue implements BlockingQueue {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    Queue<Job> q = new ArrayDeque<>();

    @Override
    public void enqueue(Job job) {
        lock.lock();
        try {
            q.offer(job);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Job dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (q.isEmpty()) notEmpty.await();

            return q.poll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getSize() {
        lock.lock();
        try {
            return q.size();
        } finally {
            lock.unlock();
        }
    }
}
