package study.ocp.concurrency.executors;

import java.util.concurrent.*;

public class ScheduledFixedRateTask {
    public static void main(String... args) throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Runnable r = () -> System.out.println("Hello world");

        service.scheduleAtFixedRate(r, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);
        service.shutdown();
    }
}
