package study.ocp.concurrency;

// // 2) Producer–Consumer Bounded Buffer
// Implement a bounded queue of capacity k where:
// * Producers block when full
// * Consumers block when empty
// Do it in two ways:
// 1. using BlockingQueue (easy)
// 2. using wait()/notifyAll() (OCP classic)
// What you practice: intrinsic locks, wait/notify, avoiding missed signals.
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.*;
class BlockingQueueProducerConsumer{
	public static void main(String... args){
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(10);

		ScheduledExecutorService heartbeatService = Executors.newScheduledThreadPool(2);
		Runnable heartbeat = ()->System.out.println(blockingQueue);
		
		ScheduledExecutorService producers = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService producers2 = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService producers3 = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService producers4 = Executors.newScheduledThreadPool(2);
		Runnable produce = ()->{
			int max = ThreadLocalRandom.current().nextInt(1,10);
			IntStream.range(1,max).forEach(i->{
				try{
					System.out.println("producing "+i);
					blockingQueue.put(i);
				}catch(InterruptedException e){}
			});
			
		};

		ScheduledExecutorService consumers = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService consumers2 = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService consumers3 = Executors.newScheduledThreadPool(2);
		ScheduledExecutorService consumers4 = Executors.newScheduledThreadPool(2);
		Runnable consume = ()->{
			int max = ThreadLocalRandom.current().nextInt(1,10);
			for(int i=0;i<max;i++){
				try{
					var val = blockingQueue.take();
					System.out.println("consuming "+val);
				}catch(InterruptedException e){}
			}
		};

		producers.scheduleAtFixedRate(produce,1L,3L,TimeUnit.SECONDS);
		producers2.scheduleAtFixedRate(produce,2L,3L,TimeUnit.SECONDS);
		producers3.scheduleAtFixedRate(produce,3L,3L,TimeUnit.SECONDS);
		producers4.scheduleAtFixedRate(produce,4L,3L,TimeUnit.SECONDS);

		consumers.scheduleAtFixedRate(consume,5L,1L,TimeUnit.SECONDS);
		// consumers2.scheduleAtFixedRate(consume,6L,1L,TimeUnit.SECONDS);
		// consumers3.scheduleAtFixedRate(consume,7L,1L,TimeUnit.SECONDS);
		// consumers4.scheduleAtFixedRate(consume,8L,1L,TimeUnit.SECONDS);

		heartbeatService.scheduleAtFixedRate(heartbeat,0L,1L,TimeUnit.SECONDS);
	}
}