package study.ocp.concurrency;

// Reentrant lock
import java.util.concurrent.locks.*;
import java.util.concurrent.*;
class ReentrantLockTryLock{
	public static void main(String... args){
		ExecutorService service = Executors.newFixedThreadPool(20);
		Lock lock = new ReentrantLock();
		Runnable r = ()->{
			try{
				if(lock.tryLock(10,TimeUnit.SECONDS)){
					lock.lock(); // bug
					System.out.println("Hello from "+Thread.currentThread());
					Thread.sleep(2_000);
					lock.unlock();
				}else{
					System.out.println(Thread.currentThread()+" - another thread won :( ");
				}
			}catch(InterruptedException e){}
			
		};
		for(int i=0;i<20;i++)
			service.submit(r);
		service.shutdown();
	}
}