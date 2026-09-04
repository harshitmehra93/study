package study.ocp.concurrency.coordination;

// // 2) Producer–Consumer Bounded Buffer
// Implement a bounded queue of capacity k where:
// * Producers block when full
// * Consumers block when empty
// Do it in two ways:
// 1. using BlockingQueue (easy)
// 2. using wait()/notifyAll() (OCP classic)
// What you practice: intrinsic locks, wait/notify, avoiding missed signals.
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class CustomBoundedQueueProducerConsumer {
    public static void main(String... args) {
        MyBlockingQueue blockingQueue = new MyBlockingQueue();

        ScheduledExecutorService heartbeatService = Executors.newScheduledThreadPool(2);
        Runnable heartbeat =
                () -> {
                    // try {
                    blockingQueue.printQueue();
                    // } catch (Exception t) {
                    // 	t.printStackTrace();
                    // 	throw t;
                    // }
                };

        ScheduledExecutorService producers = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService producers2 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService producers3 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService producers4 = Executors.newScheduledThreadPool(2);
        Runnable produce =
                () -> {
                    int max = (int) (Math.random() * 10);
                    max = max < 0 ? max * -1 : max;
                    // System.out.println("MAX - "+max);
                    try {
                        IntStream.range(1, max).forEach(blockingQueue::offer);
                    } catch (RuntimeException e) {
                        // System.out.println(e.getMessage());
                    }
                };

        ScheduledExecutorService consumers = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService consumers2 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService consumers3 = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService consumers4 = Executors.newScheduledThreadPool(2);
        Runnable consume =
                () -> {
                    try {
                        int max = (int) (Math.random() * 10);
                        max = max < 0 ? max * -1 : max;
                        for (int i = 0; i < max; i++) blockingQueue.poll();
                        // System.out.println(Thread.currentThread()+" Consuming
                        // "+blockingQueue.poll());

                        // System.out.println(Thread.currentThread()+" Consuming
                        // "+blockingQueue.poll());
                        // System.out.println(Thread.currentThread()+" Consuming
                        // "+blockingQueue.poll());
                        // blockingQueue.poll();
                        // blockingQueue.poll();
                        // blockingQueue.poll();
                    } catch (RuntimeException e) {
                        // System.out.println(e.getMessage());
                    }
                };

        producers.scheduleAtFixedRate(produce, 1L, 3L, TimeUnit.SECONDS);
        producers2.scheduleAtFixedRate(produce, 2L, 3L, TimeUnit.SECONDS);
        producers3.scheduleAtFixedRate(produce, 3L, 3L, TimeUnit.SECONDS);
        producers4.scheduleAtFixedRate(produce, 4L, 3L, TimeUnit.SECONDS);

        consumers.scheduleAtFixedRate(consume, 5L, 1L, TimeUnit.SECONDS);
        // consumers2.scheduleAtFixedRate(consume,6L,1L,TimeUnit.SECONDS);
        // consumers3.scheduleAtFixedRate(consume,7L,1L,TimeUnit.SECONDS);
        // consumers4.scheduleAtFixedRate(consume,8L,1L,TimeUnit.SECONDS);

        heartbeatService.scheduleAtFixedRate(heartbeat, 0L, 1L, TimeUnit.SECONDS);
    }
}

interface CommonQueue<T> {
    T poll();

    void offer(T job);

    int size();

    int capacity();
}

class MyBlockingQueue implements CommonQueue<Integer> {
    BlockingQueue<Integer> queue = new LinkedBlockingQueue();
    int capacity = 10;

    public Integer poll() {
        if (size() > 0) return queue.poll();
        else throw new RuntimeException("Queue is empty");
    }

    public void offer(Integer job) {
        if (size() < capacity) {
            // System.out.println(Thread.currentThread()+" is adding to queue "+job);
            queue.offer(job);
            // System.out.println("queue size "+size());
        } else {
            throw new RuntimeException("Queue is full");
        }
    }

    public void printQueue() {
        System.out.println(queue);
    }

    public int size() {
        return queue.size();
    }

    public int capacity() {
        return capacity;
    }
}
