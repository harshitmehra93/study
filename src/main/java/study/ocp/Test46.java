import java.util.concurrent.*;
class Test46{
	public static void main(String... args){
		ExecutorService service = Executors.newSingleThreadExecutor();
		Callable<Integer> c = ()->{
			Thread.sleep(1_000);
			throw new InterruptedException("Test Exception");
			// return 1;
		};
		Runnable r = ()->{
			// Thread.sleep(1_000);
			// throw new InterruptedException("Test Exception");
			// return 1;
		};
		try{
			Future result = service.submit(c);
			System.out.println(result.get());
		}
		 catch(InterruptedException | ExecutionException e){
			e.printStackTrace();
		}
		// Future<Integer> result = service.submit(c);
		// 	System.out.println(result.get());
		// service.submit(r);
		// service.execute(r);
		service.shutdown();
		
	}
}