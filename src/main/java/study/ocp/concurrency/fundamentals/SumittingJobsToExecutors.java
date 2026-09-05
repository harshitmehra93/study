package study.ocp.concurrency.fundamentals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SumittingJobsToExecutors {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable r = () -> System.out.println(finalI);
            service.submit(r);
        }
        service.shutdown();
    }
}
