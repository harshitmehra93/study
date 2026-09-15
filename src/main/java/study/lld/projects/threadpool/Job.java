package study.lld.projects.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Job<T> {
    CompletableFuture<T> future;
    Callable<T> callable;

    public void execute() {
        try {
            T result = callable.call();
            future.complete(result);
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
    }
}
