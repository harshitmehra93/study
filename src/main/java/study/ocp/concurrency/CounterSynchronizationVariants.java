package study.ocp.concurrency;

// Implement Counter with these variants:
// * PlainCounter (int, no sync) → demonstrate wrong results
// * SynchronizedCounter (synchronized)
// * LockCounter (ReentrantLock)
// * AtomicCounter (AtomicInteger)
// * AdderCounter (LongAdder)
// Task: spawn N threads, each increments M times, print final count + timing.
// What you practice: race conditions, atomicity, locks, performance tradeoffs.
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
class SynchronizedCounter{
	static int counter = 0;
	public static void main(String... args) throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(20);
		Runnable r = ()->incrementCounter();

		for(int i=0;i<21;i++){
			service.submit(r);
		}

		service.awaitTermination(3, TimeUnit.SECONDS);
		service.shutdown();

		System.out.println(counter);
	}
	static synchronized void incrementCounter(){
		counter++;
	}
}

class LocksCounter{
	static int counter = 0;
	static Lock lock = new ReentrantLock();
	public static void main(String... args) throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(20);
		Runnable r = ()->incrementCounter();

		for(int i=0;i<20;i++){
			service.submit(r);
		}

		service.awaitTermination(5, TimeUnit.SECONDS);
		service.shutdown();

		System.out.println(counter);
	}
	static void incrementCounter(){
		try{
			if(lock.tryLock(3,TimeUnit.SECONDS)){
				counter++;
				lock.unlock();
			}else{
				System.out.println(Thread.currentThread()+" couldnt get lock");
			}
		}catch(InterruptedException e){}	
		
	}
}

class AtomicCounter{
	static AtomicInteger counter = new AtomicInteger(0);
	public static void main(String... args) throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(20);
		Runnable r = ()->counter.getAndIncrement();

		for(int i=0;i<20;i++){
			service.submit(r);
		}

		service.awaitTermination(1, TimeUnit.SECONDS);
		service.shutdown();

		System.out.println(counter.get());
	}
}