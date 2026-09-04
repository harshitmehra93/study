import java.util.concurrent.*;
public class Test40{
	public static void main(String... args) throws Exception{
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		Runnable r = ()->System.out.println("Hello world");

		service.scheduleAtFixedRate(r,1,1,TimeUnit.SECONDS);

		Thread.sleep(5000);
		service.shutdown();
	}
}