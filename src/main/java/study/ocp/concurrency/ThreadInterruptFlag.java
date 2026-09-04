package study.ocp.concurrency;

import java.util.concurrent.*;
import java.util.stream.*;
import java.util.*;
import static java.lang.Thread.sleep;
public class ThreadInterruptFlag{

	public static void main(String... args) throws InterruptedException, ExecutionException{
		Thread t = new Thread(() -> {
	    	while(!Thread.currentThread().isInterrupted()){}
		});

		t.start();
		t.interrupt();
	}
}