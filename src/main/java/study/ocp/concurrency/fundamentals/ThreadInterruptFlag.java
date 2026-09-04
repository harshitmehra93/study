package study.ocp.concurrency.fundamentals;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class ThreadInterruptFlag {

    public static void main(String... args) throws InterruptedException, ExecutionException {
        Thread t =
                new Thread(
                        () -> {
                            while (!Thread.currentThread().isInterrupted()) {}
                        });

        t.start();
        t.interrupt();
    }
}
