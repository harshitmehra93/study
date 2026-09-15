package study.lld.projects.banktransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;

class BankAccount {

    private int balance;
    @Getter private final Long id;
    @Getter private final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    BankAccount() {
        this.id = ID_GENERATOR.incrementAndGet();
        this.balance = 0;
    }

    void unsafeWithdraw(int amount) {
        if (getUnsafeBalance() < amount) {
            throw new RuntimeException("insufficient balance");
        }
        if (amount <= 0) {
            throw new RuntimeException("invalid amount");
        }
        balance = balance - amount;
    }

    void unsafeDeposit(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("invalid amount");
        }
        balance = balance + amount;
    }

    int getUnsafeBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BankAccount account) {
            return Objects.equals(account.getId(), this.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

interface BankAccountRepo {
    BankAccount create();

    void save(BankAccount account);

    void update(BankAccount account);

    BankAccount findById(long id);
}

class InMemoryBankAccountRepo implements BankAccountRepo {

    List<BankAccount> accounts = new ArrayList<>();

    @Override
    public BankAccount create() {
        BankAccount account = new BankAccount();
        save(account);
        return account;
    }

    @Override
    public void save(BankAccount account) {
        accounts.add(account);
    }

    @Override
    public void update(BankAccount account) {
        BankAccount exisitng = findById(account.getId());
        accounts.remove(exisitng);
        accounts.add(account);
    }

    @Override
    public BankAccount findById(long id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst().get();
    }
}

class BankAccountService {
    private final BankAccountRepo bankAccountRepo;

    public BankAccountService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    BankAccount create() {
        return bankAccountRepo.create();
    }

    void withdraw(BankAccount account, int amount) {
        account.getLOCK().lock();
        try {
            account.unsafeWithdraw(amount);
            bankAccountRepo.update(account);
        } finally {
            account.getLOCK().unlock();
        }
    }

    void deposit(BankAccount account, int amount) {
        account.getLOCK().lock();
        try {
            account.unsafeDeposit(amount);
            bankAccountRepo.update(account);
        } finally {
            account.getLOCK().unlock();
        }
    }

    void transfer(BankAccount fromAc, BankAccount toAc, int amount) {
        tryToGetBothLocksInIncreasingOrderOfId(fromAc, toAc);
        try {
            fromAc.unsafeWithdraw(amount);
            toAc.unsafeDeposit(amount);
            bankAccountRepo.update(fromAc);
            bankAccountRepo.update(toAc);
        } finally {
            fromAc.getLOCK().unlock();
            toAc.getLOCK().unlock();
        }
    }

    private void tryToGetBothLocksInIncreasingOrderOfId(
            BankAccount accountA, BankAccount accountB) {
        if (accountA.getId() <= accountB.getId()) {
            accountA.getLOCK().lock();
            accountB.getLOCK().lock();
        } else {
            accountB.getLOCK().lock();
            accountA.getLOCK().lock();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccountRepo repo = new InMemoryBankAccountRepo();
        BankAccountService service = new BankAccountService(repo);

        BankAccount a = service.create();
        service.deposit(a, 1000);

        BankAccount b = service.create();
        service.deposit(b, 1000);

        service.transfer(a, b, 500);
        service.transfer(b, a, 500);

        System.out.println(a.getUnsafeBalance());
        System.out.println(b.getUnsafeBalance());
    }
}
