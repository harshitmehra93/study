import java.util.concurrent.*;
public class Test39{
	public static void main(String... args) throws Exception{
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> result = service.submit(()->1+1);
		System.out.println(result.get());

		service.execute(()->{while(true){}});
		service.execute(()->System.out.println("Hello2"));
		service.execute(()->System.out.println("Hello3"));
		service.execute(()->System.out.println("Hello4"));
		System.out.println(service.isShutdown());
		System.out.println(service.isTerminated());
		service.shutdown();
		System.out.println(service.isShutdown());
		System.out.println(service.isTerminated());
	}
}