package study.ocp.concurrency.executors;

import java.util.concurrent.*;

public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable job = () -> System.out.println("Hello");
        service.execute(job);
        service.shutdown();
    }
}
