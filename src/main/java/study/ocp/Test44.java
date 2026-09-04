/*

4️⃣ Fixed Thread Pool


ExecutorService service = Executors.newFixedThreadPool(2);

for(int i = 0; i < 4; i++) {
    final int x = i;
    service.submit(() -> {
        System.out.print(x + " ");
    });
}
service.shutdown();

👉 Is order guaranteed? Why or why not?

*/
import java.util.concurrent.*;
class Test44{
    public static void main(String... args){
        ExecutorService service = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 4; i++) {
            final int x = i;
            service.submit(() -> {
                System.out.print(x + " ");
            });
        }
        service.shutdown();
    }
}
