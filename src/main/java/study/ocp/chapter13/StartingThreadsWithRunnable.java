package study.ocp.chapter13;

class StartingThreadsWithRunnable {
    public static void main(String... args) {
        Runnable helloworld = () -> System.out.println("Hello World");
        Runnable a = () -> System.out.println("AAAA");
        Runnable b = () -> System.out.println("BBBB");
        Runnable c = () -> System.exit(0);

        new Thread(helloworld).start();
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}
