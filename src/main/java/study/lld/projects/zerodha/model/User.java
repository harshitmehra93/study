package study.lld.projects.zerodha.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class User {
    static final AtomicInteger counter = new AtomicInteger(0);
    public int id;
    String name;
    public int accountBalance;
    public ReentrantLock lock = new ReentrantLock();

    public int getAccountBalance() {
        return accountBalance;
    }

    public User(String name) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.accountBalance = 0;
        lock = new ReentrantLock();
    }
}
