package study.lld.projects.bankaccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBankAccount implements BankAccount {
    int balance = 0;

    private final Lock lock = new ReentrantLock();

    @Override
    public void withdraw(int amount) {
        lock.lock();
        try {
            if (amount < 0) {
                return;
            }
            if (getBalance() >= amount) {
                int bal = getBalance();
                bal = bal - amount;
                balance = bal;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deposit(int amount) {
        lock.lock();
        try {
            if (amount < 0) {
                return;
            }
            int bal = getBalance();
            bal = bal + amount;
            balance = bal;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
