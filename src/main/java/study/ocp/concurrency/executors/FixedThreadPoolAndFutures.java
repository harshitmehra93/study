package study.ocp.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
creates a fixed pool of 3 threads
submits 10 Callable<Integer> tasks
each task receives a number n and returns n * n
store every Future<Integer>
print all results
gracefully shut down the executor
 */

/*
Is result printing necessarily in task completion order? No, result is in invoke order
Does submit() block? no
Where might blocking actually happen? at get
 */
public class FixedThreadPoolAndFutures {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            int n = i;
            Callable<Integer> task = () -> n * n;
            results.add(service.submit(task));
        }

        results.forEach(
                f -> {
                    try {
                        System.out.println(f.get());
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}
