package study.lld.projects.bankaccount;

public class SimpleBankAccount implements BankAccount {
    int balance = 0;

    @Override
    public void withdraw(int amount) {
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
    public void deposit(int amount) {
        if (amount < 0) {
            return;
        }
        int bal = getBalance();
        bal = bal + amount;
        balance = bal;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
