package study.ocp.concurrency.futures;

// 🔹 Level 3 — Callable & Future
// 6️⃣ Callable Return
// Write a Callable that returns the sum of numbers 1–100.
// Retrieve result using Future.
// 👉 What happens if you call get() before task finishes?
// 👉 What happens if you never call get()?
import java.util.concurrent.*;

class CallableFutureSum {
    public static void main(String... args) {
        Callable<Integer> sum =
                () -> {
                    int sum1 = 0;
                    for (int i = 1; i <= 5; i++) sum1 += i;
                    return sum1;
                };
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> result = service.submit(sum);

        try {
            System.out.println(result.get());
        } catch (Exception e) {
        }
        service.shutdown();
    }
}
