package study.ocp.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorsCreation {
    public static void main(String[] args) {
        ExecutorService a = Executors.newSingleThreadExecutor();
        ExecutorService b = Executors.newFixedThreadPool(4);
        ExecutorService c = Executors.newCachedThreadPool();
        ScheduledExecutorService d = Executors.newScheduledThreadPool(2);
    }
}
