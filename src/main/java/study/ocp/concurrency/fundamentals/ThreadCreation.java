package study.ocp.concurrency.fundamentals;

/*
1️⃣ Thread Creation
Write two programs:
* One using extends Thread
* One using implements Runnable
Both should print numbers 1–5 with the thread name.
👉 Question:What happens if you call run() instead of start()?
*/
public class ThreadCreation {
    public static void main(String... args) {
        Runnable a = () -> System.out.println("runnable");
        new Thread(a).start();
    }
}

class TestThread extends Thread {
    public TestThread(Runnable job) {
        super(job);
    }
}

class MyThread extends Thread {
    public MyThread(Runnable job) {
        super(job);
    }

    public MyThread() {}

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) System.out.println(getName() + " " + i);
    }
}

class Job implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            System.out.println("Runnable Job -> " + Thread.currentThread() + " " + i);
    }
}
