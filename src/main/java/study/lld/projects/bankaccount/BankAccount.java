package study.lld.projects.bankaccount;

public interface BankAccount {
    void withdraw(int amount);

    void deposit(int balance);

    int getBalance();
}
