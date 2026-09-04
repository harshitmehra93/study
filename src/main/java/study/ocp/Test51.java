import java.util.concurrent.*;
import java.util.stream.*;
import java.util.*;
import static java.lang.Thread.sleep;
public class Test51{

	public static void main(String... args) throws InterruptedException, ExecutionException{
		Thread t = new Thread(() -> {
	    	while(!Thread.currentThread().isInterrupted()){}
		});

		t.start();
		t.interrupt();
	}
}