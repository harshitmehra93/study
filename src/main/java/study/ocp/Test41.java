/*
1️⃣ Thread Creation
Write two programs:
* One using extends Thread
* One using implements Runnable
Both should print numbers 1–5 with the thread name.
👉 Question:What happens if you call run() instead of start()?
*/
public class Test41{
	public static void main(String... args){
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();
		t1.setName("A");
		t1.start();
		// t1.run();

		t2.setName("B");
		t2.start();
		// t2.run();

		t3.setName("C");
		t3.start();
		// t3.run();

		// Job job = new Job();
		// // Thread t4 = new Thread(job);
		// // Thread t5 = new Thread(job);
		// // Thread t6 = new Thread(job);

		// // t4.start();
		// // t5.start();
		// // t6.start();

		// MyThread t4 = new MyThread(job);
		// MyThread t5 = new MyThread(job);
		// MyThread t6 = new MyThread(job);

		// t4.start();
		// t5.start();
		// t6.start();
	}
}

class MyThread extends Thread{
	public MyThread(Runnable job){
		super(job);
	}
	public MyThread(){
	}
	@Override
	public void run(){
		for(int i=0;i<5;i++)
			System.out.println(getName()+" "+i);
	}
}

class Job implements Runnable{
	@Override
	public void run(){
		for(int i=0;i<5;i++)
			System.out.println("Runnable Job -> "+Thread.currentThread()+" "+i);
	}
}