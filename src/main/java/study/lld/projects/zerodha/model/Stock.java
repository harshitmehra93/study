package study.lld.projects.zerodha.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Stock {
    static final AtomicInteger counter = new AtomicInteger(0);
    public int id;
    String name;
    public int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
        this.id = counter.getAndIncrement();
    }
}
