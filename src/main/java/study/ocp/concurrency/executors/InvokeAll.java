package study.ocp.concurrency.executors;

import java.util.List;
import java.util.concurrent.*;

/*
make 3 callable
A → sleeps 3 sec → returns 10
B → sleeps 1 sec → returns 20
C → sleeps 2 sec → returns 30

call them individually and then use invokeAll
 */
public class InvokeAll {
    public static void main(String[] args) throws Exception {
        Callable<Integer> a =
                () -> {
                    Thread.sleep(3000);
                    return 10;
                };
        Callable<Integer> b =
                () -> {
                    Thread.sleep(1000);
                    return 20;
                };
        Callable<Integer> c =
                () -> {
                    Thread.sleep(2000);
                    return 30;
                };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("===individual submit===");
        executor.submit(a);
        executor.submit(b);
        executor.submit(c);
        System.out.println("===done===");

        System.out.println("===invokeAll===");
        List<Future<Integer>> results = executor.invokeAll(List.of(a, b, c));
        results.forEach(
                f -> {
                    try {
                        System.out.println(f.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });
        System.out.println("===invokeAllEnd===");

        executor.shutdown();
    }
}
