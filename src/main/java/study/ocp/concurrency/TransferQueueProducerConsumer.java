package study.ocp.concurrency;

// TransferQueue
import java.util.concurrent.*;
class TransferQueueProducerConsumer{
	public static void main(String... args){
		TransferQueue<Integer> q = new LinkedTransferQueue<>();
		ScheduledExecutorService producer = Executors.newScheduledThreadPool(5);
		ScheduledExecutorService consumer = Executors.newScheduledThreadPool(5);
		Runnable produce = ()->{
			for(int i=0;i<1000;i++){
				try { 
			  		System.out.println("Producing "+i);
			  		q.transfer(i);
			  		System.out.println("Transferred "+i);
		  		} catch (InterruptedException e) {

		  		}
			}
		};

		Runnable consume = ()->{
			try{
				while(true){
					Thread.sleep(5_000);
					System.out.println("Consuming "+q.take());
				}
			}catch (InterruptedException e) {
				
			}
			
		};

		producer.scheduleAtFixedRate(produce,1L,1L,TimeUnit.SECONDS);
		consumer.scheduleAtFixedRate(consume,1L,3L,TimeUnit.SECONDS);
	}
}