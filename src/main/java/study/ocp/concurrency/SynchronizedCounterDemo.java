package study.ocp.concurrency;

// 🔹 Level 4 — Synchronization & Race Conditions (CRITICAL)
// 8️⃣ Race Condition Demo
// Write this:


// class Counter {
//     int count = 0;
//     void increment() {
//         count++;
//     }
// }

// Start 100 threads incrementing 1000 times.
// 👉 Why is result inconsistent?
// Fix it using:
// * synchronized 
// * AtomicInteger
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
class SynchronizedCounterDemo{
	public static void main(String... args) throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(100);
		Counter counter = new Counter();
		Runnable r = ()->{
			counter.increment();
			System.out.println(Thread.currentThread()+" "+counter.count);
		};

		for(int i=0;i<1000;i++)
			service.submit(r);

		service.awaitTermination(2, TimeUnit.SECONDS);

		System.out.println("counter = "+counter.count);

		service.shutdown();
		
	}
}

class Counter {
    // AtomicInteger count = new AtomicInteger();
    volatile int count=0;
    Counter(){
    	// count.set(0);
    }
    void increment() {
    	synchronized(Counter.class){
    		count++;
    	}
        // count.getAndIncrement();
    }
}