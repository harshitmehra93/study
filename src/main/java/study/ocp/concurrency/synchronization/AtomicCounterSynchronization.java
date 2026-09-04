package study.ocp.concurrency.synchronization;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class AtomicCounterSynchronization {

    static AtomicInteger counter = new AtomicInteger();
    static ArrayList<Integer> list = new ArrayList<>();

    public static synchronized void theJob() {
        list.add(counter.incrementAndGet());
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        counter.set(0);
        Runnable job = () -> theJob();

        for (int i = 0; i < 10; i++) service.submit(job);

        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        Collections.sort(list);
        System.out.println(list);
    }
}
