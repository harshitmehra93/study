import java.util.concurrent.*;
class Test48{
    public static void main(String... args){
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> result = service.submit(() -> {
            Thread.sleep(5000);
            return 10;
        });

        try{System.out.println(result.get(1, TimeUnit.SECONDS));}
        catch(Exception e){e.printStackTrace();}
        service.shutdown();
    }
}
