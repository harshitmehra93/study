package study.ocp.concurrency.threadstates;

// 2️⃣ Thread State Reasoning
// What are possible states of a thread?
// Write a small program that demonstrates:
// * NEW 
// * RUNNABLE 
// * BLOCKED 
// * TERMINATED 
// Explain how you forced each state.

public class ThreadStateObservation{
	public static void main(String... args) throws InterruptedException{
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		// MyThread t3 = new MyThread();
		t1.setName("A");
		t2.setName("B");
		System.out.println("State "+t1.getName()+" = "+t1.getState()); // NEW
		System.out.println("State "+t2.getName()+" = "+t2.getState()); // NEW
		t1.start(); // RUNNABLE
		t2.start(); // RUNNABLE

		while(true){
			System.out.println("State "+t1.getName()+" = "+t1.getState()); // RUNNABLE/Terminated/Blocked/TIMED_WAITING
			System.out.println("State "+t2.getName()+" = "+t2.getState()); // RUNNABLE/Terminated/Blocked/TIMED_WAITING
			Thread.sleep(2000);
		}

	}

}
class MyThread extends Thread{
	private static final Object LOCK = new Object();
	@Override
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(getName()+" "+i);
			criticalSection(this);
		}
			
	}
	static void criticalSection(MyThread thread){
		synchronized(MyThread.class){
			System.out.println("Thread "+thread.getName()+" entered critical section");
			try{Thread.sleep(5_000);}
			catch(InterruptedException e){}
			System.out.println("Thread "+thread.getName()+" exited critical section");
		}
		thread.yield();
	}
}