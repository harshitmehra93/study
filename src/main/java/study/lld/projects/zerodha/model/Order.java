package study.lld.projects.zerodha.model;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    static final AtomicInteger counter = new AtomicInteger(0);
    public int id;
    public int userId;
    public OrderStatus orderStatus;
    public Instant submittedTime;

    public Order() {
        this.id = counter.getAndIncrement();
        this.submittedTime = Instant.now();
    }

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
}
