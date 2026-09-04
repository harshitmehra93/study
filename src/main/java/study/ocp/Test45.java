/*

5️⃣ submit() vs execute()
What is the difference between:


service.execute(...)
service.submit(...)

When would an exception be swallowed?

*/
import java.util.concurrent.*;
class Test45{
    public static void main(String... args) throws InterruptedException, ExecutionException{
        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable r = ()->{while(true){try{Thread.sleep(100000);}catch(Exception e){}break;}};
        Callable c = ()->1;
        // service.execute(r);
        Future future = service.submit(r);
        System.out.println("is done = "+future.isDone());
        System.out.println("cancelled = "+future.cancel(true));
        future.get();

        // service.execute(c);
        service.submit(c);
        service.shutdown();
    }
}

/*

Consumer<T>{
	void consume(T a);
}

Supplier<T>{
	T get();
}

Function<T,U>{
	T function(U a);
}

UnaryOperator<T>{
	T operate(T a);
}

BiConsumer<T>{
	void consume(T a);
}

BiFunction<T,U,V>{
	T function(U a, U b);
}

BinaryOperator<T>{
	T operate(T a, T b);
}

*/
