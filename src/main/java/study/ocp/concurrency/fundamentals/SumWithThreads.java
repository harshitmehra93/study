package study.ocp.concurrency.fundamentals;

import java.util.concurrent.*;

// executor with 2 threads
// submit Callable<Integer>
// calculate 10 + 20
// obtain result
// shut executor down
public class SumWithThreads {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Integer> sum = () -> 10 + 20;
        Future<Integer> result = service.submit(sum);
        System.out.println(result.get());
        service.shutdown();
    }
}
