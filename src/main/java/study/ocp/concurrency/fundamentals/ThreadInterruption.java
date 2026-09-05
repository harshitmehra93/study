package study.ocp.concurrency.fundamentals;

public class ThreadInterruption {
    static int i = 0;
    static int j = 0;

    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        Runnable job1 =
                () -> {
                    for (; i < 10; i++) {
                        System.out.println(Thread.currentThread() + " - " + i);
                    }
                    main.interrupt();
                    System.exit(0);
                };
        Runnable job2 =
                () -> {
                    for (; j < 10; j++) {
                        System.out.println(Thread.currentThread() + " - " + j);
                    }
                    main.interrupt();
                    System.exit(0);
                };
        new Thread(job1).start();
        new Thread(job2).start();

        try {
            while (true) Thread.sleep(1_00_000);
        } catch (InterruptedException i) {
            System.out.println("interrupted!");
        }
    }
}
