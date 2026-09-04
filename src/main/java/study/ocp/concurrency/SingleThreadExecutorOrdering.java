package study.ocp.concurrency;

/*
🔹 Level 2 — ExecutorService (Very Important for OCP)
3️⃣ Single Thread Executor
What is the output of this?


ExecutorService service = Executors.newSingleThreadExecutor();
service.submit(() -> System.out.print("A"));
service.submit(() -> System.out.print("B"));
service.submit(() -> System.out.print("C"));
service.shutdown();

👉 Can the order ever change?
*/
import java.util.concurrent.*;
class SingleThreadExecutorOrdering{
	public static void main(String... args){
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(() -> System.out.print("A"));
		service.submit(() -> System.out.print("B"));
		service.submit(() -> System.out.print("C"));
		service.shutdown();
	}
}