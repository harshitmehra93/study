package study.ocp.concurrency.concurrentcollections;

// 11️⃣ CopyOnWriteArrayList
// What happens if one thread iterates while another modifies?
// Why does it not throw ConcurrentModificationException?
// Tradeoff?
import java.util.*;
import java.util.concurrent.*;

class CopyOnWriteArrayListIteration {
    public static void main(String... args) throws InterruptedException {
        var foodData = new CopyOnWriteArrayList<Integer>();
        Runnable adder =
                () -> {
                    System.out.println("adding");
                    for (int i = 0; i < 1000; i++) {
                        foodData.add(1);
                        foodData.add(2);
                        foodData.add(3);
                    }
                };
        Runnable remover =
                () -> {
                    System.out.println("removing");
                    for (int i = 0; i < 1000; i++) foodData.remove(1);
                };
        Runnable printer =
                () -> {
                    for (int i = 0; i < 100; i++) {
                        for (var el : foodData) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                            }
                            System.out.println(el);
                        }
                    }
                };
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(adder);
        service.submit(remover);
        service.submit(printer);
        service.awaitTermination(5, TimeUnit.SECONDS);
        // System.out.println(foodData);
        // System.out.println(foodData.size());
        service.shutdown();
    }
}
