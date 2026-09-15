package study.lld.projects.bankaccount;

public class SynchnoizedBankAccount implements BankAccount {
    int balance = 0;

    @Override
    public synchronized void withdraw(int amount) {
        if (amount < 0) {
            return;
        }
        if (getBalance() >= amount) {
            int bal = getBalance();
            bal = bal - amount;
            balance = bal;
        }
    }

    @Override
    public synchronized void deposit(int amount) {
        if (amount < 0) {
            return;
        }
        int bal = getBalance();
        bal = bal + amount;
        balance = bal;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
