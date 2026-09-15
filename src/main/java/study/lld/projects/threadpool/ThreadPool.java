package study.lld.projects.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ThreadPool {
    <T> Future<T> submit(Callable<T> c);

    void shutdown();
}
