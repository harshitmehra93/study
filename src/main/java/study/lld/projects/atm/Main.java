package study.lld.projects.atm;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;

/*
Learnings-
- If ATM owns the state then, ATM Service should not be changing its state directly. It should just call methods on ATM and they should be managing state.
- time: 2h
 */
public class Main {
    public static void main(String[] args) {
        /*
        Design an ATM

        An Atm can have the following functionality
        GeneralInterface
        withdrawFromUserAccount(user, amount)
        depositToUserAccount(user, amount)
        checkBalanceOfUserAccount(user)
        insertCard(card)
        validateCard(card)
        ejectCard()
        authenticateCard(card)

        AdminInterface
        authenticateAdmin(user)
        addCash(Denomination, quantity)
        atmBalance()
        canDispense(amount)
        maximumCashLimit()

        Atm States -
        INIT - READY - AUTHENTICATED - CARD_INSERTED, DEPOSITING - WITHDRAWING

        Atm{
         - id
         - state
         - atmCash<Denomination, quantity>
         - maximumCashLimit
         - vendCash(amount)
         - acceptCash(amount)
         - insertCard(card)
         - ejectCard()
         - addCashToAtm(Denomination, quantity)
         - setCurrentSession(session)
         - endCurrentSession
         - reset()
        }
        Session{
        - User
        - Card
        - startedAt
        }

        SessionService{
        - CardService
        - createSession(Card, User, ttl)
        - endSession()
        }

        GeneralAtmService{
            SessionService
            CashValidator
            withdrawFromUserAccount(session, amount, preferredDenomination)
            depositToUserAccount(session, amount)
            checkBalanceOfUserAccount(session)
            authenticateCard(card)
        }
        CashValidator{
            validateCash()
        }
        UserAccountService{
            withdrawFromUserAccount(user, amount)
            depositToUserAccount(user, amount)
            checkBalanceOfUserAccount(user)
        }
        CardService{
            validateCard(card)
            authenticateCard(card,pin)
            getUserAccountFromCard(card)
        }

        Card{
         - id
         - userId
         - cardNumber
         - expiry
         - cvv
         - pin
        }

         */
        CashValidator cashValidator = new CashValidator();
        UserAccountService userAccountService = new UserAccountService(new UserAccountDao());
        HSMCardAuthenticationService cardAuthenticationService = new HSMCardAuthenticationService();
        CardService cardService = new CardService(cardAuthenticationService);
        SessionService sessionService = new SessionService(cardService, userAccountService);

        Atm atm = new Atm();
        AtmService atmService =
                new AtmService(cashValidator, sessionService, userAccountService, atm);
        atm.addCashToAtm(
                List.of(
                        new DenominationQuantityPair(Denomination.HUNDRED, 5),
                        new DenominationQuantityPair(Denomination.FIFTY, 5),
                        new DenominationQuantityPair(Denomination.TEN, 5),
                        new DenominationQuantityPair(Denomination.ONE, 5)));
        atm.reset();

        UserAccount user = new UserAccount("Harshit");
        user.deposit(10_000);
        userAccountService.create(user);

        Card card = new Card(user.id, 1234, 455, Instant.now().plus(5, ChronoUnit.MINUTES), "1234");
        atmService.authenticate(card, 1234);
        atmService.checkBalanceOfUserAccount();

        atmService.authenticate(card, 1234);
        atmService.depositToUserAccount(
                List.of(new DenominationQuantityPair(Denomination.HUNDRED, 1)));

        atmService.authenticate(card, 1234);
        atmService.checkBalanceOfUserAccount();

        UserAccount user2 = new UserAccount("Smridhi");
        userAccountService.create(user2);
        Card card2 =
                new Card(user2.id, 1234, 455, Instant.now().plus(5, ChronoUnit.MINUTES), "1234");

        atmService.authenticate(card2, 1234);
        atmService.checkBalanceOfUserAccount();

        atmService.authenticate(card2, 1234);
        atmService.depositToUserAccount(
                List.of(new DenominationQuantityPair(Denomination.HUNDRED, 1)));

        atmService.authenticate(card2, 1234);
        atmService.checkBalanceOfUserAccount();

        atmService.authenticate(card2, 1234);
        atmService.withdrawFromUserAccount(101);

        atmService.authenticate(card2, 1234);
        atmService.checkBalanceOfUserAccount();

        for (int i = 0; i < 2; i++) {
            atmService.authenticate(card, 1234);
            atmService.withdrawFromUserAccount(900);
        }
    }
}

// INIT - READY - AUTHENTICATED - CARD_INSERTED, DEPOSITING - WITHDRAWING
enum AtmState {
    INIT,
    READY,
    AUTHENTICATED,
    CARD_INSERTED,
    DEPOSITING,
    WITHDRAWING
}

enum Denomination {
    ONE,
    TEN,
    FIFTY,
    HUNDRED,
    FIVE_HUNDRED
}

record DenominationQuantityPair(Denomination denomination, Integer quantity) {}

class Atm {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    private AtmState atmState;
    Map<Denomination, Integer> atmCash;
    int maximumCashLimit;
    Card currentCard;
    private Session currentSession;

    public Session getSession() {
        if (isSessionValid()) {
            return currentSession;
        }
        throw new RuntimeException("session invalid");
    }

    public void setSession(Session session) {
        if (atmState != AtmState.CARD_INSERTED) throwInvalidState();
        Objects.requireNonNull(session, "session should be non null");
        this.currentSession = session;
        atmState = AtmState.AUTHENTICATED;
    }

    public void vendCash(int amount) {
        if (atmState != AtmState.AUTHENTICATED) throwInvalidState();
        atmState = AtmState.WITHDRAWING;
        try {
            withdrawCashFromAtm(amount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reset();
        }
    }

    public void acceptCash(List<DenominationQuantityPair> cash) {
        if (atmState != AtmState.AUTHENTICATED) throwInvalidState();

        atmState = AtmState.DEPOSITING;
        System.out.println("Accepting cash");
        addCashToAtm(cash);
    }

    void insertCard(Card card) {
        if (atmState != AtmState.READY) throwInvalidState();
        System.out.printf("card %d inserted\n", card.cardNumber);
        atmState = AtmState.CARD_INSERTED;
    }

    public void ejectCard() {
        if (currentCard != null) {
            System.out.println("Ejecting card");
        }
        currentCard = null;
    }

    public void addCashToAtm(List<DenominationQuantityPair> cashSet) {
        if (!Set.of(AtmState.INIT, AtmState.DEPOSITING).contains(atmState)) throwInvalidState();
        Map<Denomination, Integer> copyOfCash = new HashMap<>(atmCash);
        boolean success = false;
        try {
            for (DenominationQuantityPair cash : cashSet) {
                copyOfCash.put(
                        cash.denomination(),
                        copyOfCash.getOrDefault(cash.denomination(), 0) + cash.quantity());
            }
            success = true;
        } catch (Exception e) {
            System.out.println("unexpected error while adding cash to atm");
        }
        if (success) {
            atmCash = copyOfCash;
        }
    }

    private void throwInvalidState() {
        System.out.println("Invalid state " + atmState);
        throw new RuntimeException("invalid state");
    }

    private void withdrawCashFromAtm(int amount) {
        if (!canWithdraw(amount)) throw new RuntimeException("cannot withdraw amount");
        if (atmState != AtmState.WITHDRAWING) {
            throwInvalidState();
        }

        List<Integer> denominations = new ArrayList<>();
        for (Denomination denomination : Denomination.values()) {
            int note = denominationToInt(denomination);
            denominations.add(note);
        }
        Collections.sort(denominations);
        List<DenominationQuantityPair> withdrawnCash = new ArrayList<>();

        Map<Denomination, Integer> copyOfCash = new HashMap<>(atmCash);
        boolean success = false;
        try {
            for (int i = denominations.size() - 1; i >= 0 && amount != 0; i--) {
                int denomination = denominations.get(i);
                if (amount < denomination) continue;
                int quantity = amount / denomination;
                quantity =
                        Math.min(
                                copyOfCash.getOrDefault(intToDenomination(denomination), 0),
                                quantity);
                if (quantity == 0) continue;
                copyOfCash.put(
                        intToDenomination(denomination),
                        copyOfCash.get(intToDenomination(denomination)) - quantity);
                withdrawnCash.add(
                        new DenominationQuantityPair(intToDenomination(denomination), quantity));
                amount = amount - (denomination * quantity);
            }
            if (amount != 0) throw new RuntimeException("cannot withdraw cash");
            System.out.println("Vending cash");
            for (DenominationQuantityPair denominationQuantityPair : withdrawnCash) {
                System.out.printf(
                        "%d notes of denomination %s\n",
                        denominationQuantityPair.quantity(),
                        denominationQuantityPair.denomination().toString());
            }
            success = true;
        } catch (Exception e) {
            System.out.println("Unexpected error while vending cash");
        }
        if (success) {
            atmCash = copyOfCash;
        }
    }

    private static int denominationToInt(Denomination denomination) {
        return switch (denomination) {
            case ONE -> 1;
            case TEN -> 10;
            case FIFTY -> 50;
            case HUNDRED -> 100;
            case FIVE_HUNDRED -> 500;
        };
    }

    private static Denomination intToDenomination(int integer) {
        return switch (integer) {
            case 1 -> Denomination.ONE;
            case 10 -> Denomination.TEN;
            case 50 -> Denomination.FIFTY;
            case 100 -> Denomination.HUNDRED;
            case 500 -> Denomination.FIVE_HUNDRED;
            default -> throw new RuntimeException("invalid note denominatipon");
        };
    }

    public Atm() {
        id = counter.getAndIncrement();
        atmState = AtmState.INIT;
        atmCash = new HashMap<>();
        maximumCashLimit = 2_000;
        currentCard = null;
        currentSession = null;
    }

    public void reset() {
        currentSession = null;
        ejectCard();
        atmState = AtmState.READY;
    }

    public void init() {
        if (atmState != AtmState.READY) throw new RuntimeException("invalid state");
        atmState = AtmState.INIT;
    }

    public boolean canDeposit(int amount) {
        if (amount + currentCash > maximumCashLimit)
            throw new RuntimeException("cannot accept this amount");
        return true;
    }

    int currentCash = 0;

    public boolean canWithdraw(int amount) {

        List<Integer> denominations = new ArrayList<>();
        for (Denomination denomination : Denomination.values()) {
            int note = denominationToInt(denomination);
            denominations.add(note);
        }
        Collections.sort(denominations);

        Map<Denomination, Integer> copyOfCash = new HashMap<>(atmCash);

        for (int i = denominations.size() - 1; i >= 0 && amount != 0; i--) {
            int denomination = denominations.get(i);
            if (amount < denominations.get(i)) continue;
            int quantity = amount / denomination;
            quantity =
                    Math.min(copyOfCash.getOrDefault(intToDenomination(denomination), 0), quantity);
            if (quantity == 0) continue;
            copyOfCash.put(
                    intToDenomination(denomination),
                    copyOfCash.get(intToDenomination(denomination)) - quantity);
            amount = amount - (denomination * quantity);
        }
        if (amount != 0) return false;
        return true;
    }

    boolean isSessionValid() {
        Objects.requireNonNull(currentSession, "session is null");
        if (currentSession.createdTime.plus(currentSession.ttl).isBefore(Instant.now())) {
            return false;
        }
        return true;
    }
}

/*
GeneralAtmService{
            SessionService
            CashValidator
            withdrawFromUserAccount(session, amount, preferredDenomination)
            depositToUserAccount(session, amount)
            checkBalanceOfUserAccount(session)
            authenticateCard(card)
        }
 */
class AtmService {
    private final CashValidator cashValidator;
    private final SessionService sessionService;
    private final UserAccountService userAccountService;
    private final Atm atm;

    AtmService(
            CashValidator cashValidator,
            SessionService sessionService,
            UserAccountService userAccountService,
            Atm atm) {
        this.cashValidator = cashValidator;
        this.sessionService = sessionService;
        this.userAccountService = userAccountService;
        this.atm = atm;
    }

    void withdrawFromUserAccount(int amount) {
        atm.isSessionValid();
        Objects.requireNonNull(amount, "cash is null");
        try {
            if (!atm.canWithdraw(amount)) {
                System.out.println("cannot withdraw amount");
                return;
            }
            // atomic transaction start
            System.out.println("hello1");
            userAccountService.withdrawFromUserAccount(atm.getSession(), amount);
            System.out.println("hello2");
            atm.vendCash(amount);
            // atomic transaction end
        } catch (Exception e) {
            System.out.println("error while withdrawing");
            e.printStackTrace();
        } finally {
            atm.reset();
        }
    }

    void depositToUserAccount(List<DenominationQuantityPair> cash) {
        atm.isSessionValid();
        Objects.requireNonNull(cash, "cash is null");
        try {
            int amount = getTotalAmount(cash);
            if (!atm.canDeposit(amount)) {
                System.out.println("cannot accept more cash");
                return;
            }
            // atomic transaction start
            atm.acceptCash(cash);
            cashValidator.validateCash(cash);
            userAccountService.depositToUserAccount(atm.getSession(), amount);
            // atomic transaction end
        } catch (Exception e) {
            System.out.println("error while depositing");
            e.printStackTrace();
        } finally {
            atm.reset();
        }
    }

    private int getTotalAmount(List<DenominationQuantityPair> cashSet) {
        int amount = 0;
        for (DenominationQuantityPair denomination : cashSet) {
            int note =
                    switch (denomination.denomination()) {
                        case ONE -> 1;
                        case TEN -> 10;
                        case FIFTY -> 50;
                        case HUNDRED -> 100;
                        case FIVE_HUNDRED -> 500;
                    };
            amount += note * denomination.quantity();
        }
        return amount;
    }

    void checkBalanceOfUserAccount() {
        if (!atm.isSessionValid()) throw new RuntimeException("session is not valid");
        try {
            System.out.println(
                    "balance is ="
                            + userAccountService.checkBalanceOfUserAccount(atm.getSession()));
        } catch (Exception e) {
            System.out.println("Unexpected error occured while getting balance");
        } finally {
            atm.reset();
        }
    }

    void authenticate(Card card, int pin) {
        try {
            atm.insertCard(card);
            atm.setSession(
                    sessionService.createSession(card, Duration.of(2, ChronoUnit.MINUTES), pin));
            System.out.println("Welcome " + atm.getSession().user.name);
        } catch (Exception e) {
            atm.reset();
        }
    }

    void cancel() {
        atm.reset();
    }
}

class CashValidator {
    void validateCash(List<DenominationQuantityPair> cash) {}
}

class Card {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int userId;
    int cardNumber;
    int cvv;
    Instant expiry;
    String pinSecretId;

    public Card(int userId, int cardNumber, int cvv, Instant expiry, String pinSecretId) {
        this.id = counter.getAndIncrement();
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiry = expiry;
        this.pinSecretId = pinSecretId;
    }
}

@AllArgsConstructor
class Session {
    UserAccount user;
    Card card;
    Duration ttl;
    Instant createdTime;
}

class UserAccount {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int balance;
    String name;
    ReentrantLock lock = new ReentrantLock();

    void withdraw(int amount) {
        lock.lock();
        try {
            if (amount <= 0) throw new RuntimeException("invalid amount");
            if (balance < amount) throw new RuntimeException("insufficient balance");
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    void deposit(int amount) {
        lock.lock();
        try {
            if (amount <= 0) throw new RuntimeException("invalid amount");
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    UserAccount(String name) {
        this.id = counter.getAndIncrement();
        balance = 0;
        this.name = name;
    }
}

class SessionService {
    private final CardService cardService;
    private final UserAccountService userAccountService;

    SessionService(CardService cardService, UserAccountService userAccountService) {
        this.cardService = cardService;
        this.userAccountService = userAccountService;
    }

    Session createSession(Card card, Duration ttl, int pin) {
        UserAccount user = userAccountService.getUser(card.userId);
        cardService.validateCard(card);
        cardService.authenticateCard(card, pin);
        if (ttl.isZero() || ttl.isNegative()) throw new RuntimeException("incorrect ttl");
        return new Session(user, card, ttl, Instant.now());
    }
}

class UserAccountService {
    private final UserAccountDao userAccountDao;

    UserAccountService(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    UserAccount getUser(int id) {
        return userAccountDao.get(id);
    }

    void create(UserAccount user) {
        userAccountDao.create(user);
    }

    void withdrawFromUserAccount(Session session, int amount) {
        session.user.withdraw(amount);
    }

    int checkBalanceOfUserAccount(Session session) {
        return session.user.getBalance();
    }

    void depositToUserAccount(Session session, int amount) {
        session.user.deposit(amount);
    }
}

class UserAccountDao {
    Map<Integer, UserAccount> accounts = new ConcurrentHashMap<>();

    UserAccount get(int id) {
        if (!accounts.containsKey(id)) throw new RuntimeException("account does exist");
        return accounts.get(id);
    }

    void create(UserAccount user) {
        accounts.put(user.id, user);
    }
}

class CardService {
    private final HSMCardAuthenticationService cardAuthenticationService;

    CardService(HSMCardAuthenticationService cardAuthenticationService) {
        this.cardAuthenticationService = cardAuthenticationService;
    }

    void validateCard(Card card) {
        if (card.expiry.isBefore(Instant.now())) {
            throw new RuntimeException("Card is expired");
        }
        System.out.println("Validating card");
        ;
    }

    void authenticateCard(Card card, int pin) {
        cardAuthenticationService.authenticateCard(card, pin);
    }
}

class HSMCardAuthenticationService {
    void authenticateCard(Card card, int pin) {
        System.out.printf("Authenticating card %d in HSM with redacted pin\n", card.id);
    }
}

/*
Card{
         - id
         - userId
         - cardNumber
         - expiry
         - cvv
         - pin
        }
 */
