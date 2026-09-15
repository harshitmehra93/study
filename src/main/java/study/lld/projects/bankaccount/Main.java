package study.lld.projects.bankaccount;

/*
Design a thread-safe BankAccount with deposit, withdraw, and getBalance.

Two threads may simultaneously try to withdraw ₹800 from an account with ₹1,000.

How would you ensure:

the balance never goes negative,
only one withdrawal succeeds,
readers see the latest balance,
and deposit/withdraw don’t corrupt each other?
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        BankingTester bankingTester =
                new TimedBankingTesterDecorator(new ConcurrentBankingTester());

        System.out.println("=====SIMPLE======");
        BankAccount bankAccount = new SimpleBankAccount();
        bankAccount.deposit(1_00_000);
        bankingTester.withdrawAndDepositAmount(bankAccount, 100, 999);
        System.out.println("balance should be " + 1_00_000);
        System.out.println("final balance = " + bankAccount.getBalance());

        System.out.println("=====SYNCHRONIZED======");
        bankAccount = new SynchnoizedBankAccount();
        bankAccount.deposit(1_00_000);
        bankingTester.withdrawAndDepositAmount(bankAccount, 100, 999);
        System.out.println("balance should be " + 1_00_000);
        System.out.println("final balance = " + bankAccount.getBalance());

        System.out.println("=====LOCK======");
        bankAccount = new LockBankAccount();
        bankAccount.deposit(1_00_000);
        bankingTester.withdrawAndDepositAmount(bankAccount, 100, 999);
        System.out.println("balance should be " + 1_00_000);
        System.out.println("final balance = " + bankAccount.getBalance());
    }
}
