package study.lld.projects.bankaccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentBankingTester implements BankingTester {
    public void withdrawAndDepositAmount(BankAccount bankAccount, int amount, int times) {
        int depositTimesLeft = times;
        int withdrawalTimesLeft = times;
        ExecutorService executor = Executors.newFixedThreadPool(50);
        while (depositTimesLeft > 0 || withdrawalTimesLeft > 0) {
            Coin toss = toss();
            if (toss == Coin.HEADS && withdrawalTimesLeft > 0) {
                executor.submit(() -> bankAccount.withdraw(amount));
                withdrawalTimesLeft--;
            } else if (toss == Coin.TAILS && depositTimesLeft > 0) {
                executor.submit(() -> bankAccount.deposit(amount));
                depositTimesLeft--;
            }
        }
        try {
            executor.shutdown();
            if (!executor.isTerminated()) {
                executor.awaitTermination(5, TimeUnit.MINUTES);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Coin toss() {
        double rand = Math.random() * 10;
        return rand >= 5 ? Coin.HEADS : Coin.TAILS;
    }

    enum Coin {
        HEADS,
        TAILS
    }
}
