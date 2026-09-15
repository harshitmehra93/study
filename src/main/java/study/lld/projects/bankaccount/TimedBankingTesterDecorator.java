package study.lld.projects.bankaccount;

import java.time.Instant;

public class TimedBankingTesterDecorator implements BankingTester {
    private final BankingTester bankingTester;

    public TimedBankingTesterDecorator(BankingTester bankingTester) {
        this.bankingTester = bankingTester;
    }

    @Override
    public void withdrawAndDepositAmount(BankAccount bankAccount, int amount, int times) {
        Instant before = Instant.now();
        bankingTester.withdrawAndDepositAmount(bankAccount, amount, times);
        Instant after = Instant.now();
        long millis = after.toEpochMilli() - before.toEpochMilli();
        System.out.println("time elapsed = " + millis + " ms");
    }
}
