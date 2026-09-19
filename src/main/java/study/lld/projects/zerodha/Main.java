package study.lld.projects.zerodha;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new InMemoryUserDao();
        PortfolioDao portfolioDao = new InMemoryPortfolio();
        UserService userService = new UserService(userDao, portfolioDao);

        StockDao stockDao = new InMemoryStockDao();
        StockService stockService = new StockService(stockDao);

        BankingOrderDao bankingOrderDao = new InMemoryBankingOrderDao();
        StockOrderDao stockOrderDao = new InMemoryStockOrderDao();
        OrderService orderService = new OrderService(bankingOrderDao, stockOrderDao);
        ZerodhaService zerodhaService = new ZerodhaService(userService, stockService, orderService);

        User a = new User("Harshit");
        User b = new User("Pulkit");
        userService.save(a);
        userService.save(b);

        Stock amzn = new Stock("AMZN", 10);
        Stock msft = new Stock("MSFT", 20);
        Stock orcl = new Stock("ORCL", 30);

        stockService.save(amzn);
        stockService.save(msft);
        stockService.save(orcl);

        zerodhaService.depositMoney(a.id, 100);

        zerodhaService.buyStock(a.id, amzn.id, 3);
        zerodhaService.buyStock(a.id, orcl.id, 1);
        zerodhaService.buyStock(a.id, msft.id, 1);

        zerodhaService.sellStock(a.id, msft.id, 1);
        zerodhaService.sellStock(a.id, orcl.id, 1);

        userService.depositToUserAccount(a.id, 100);
        userService.withdrawFromUserAccount(a.id, 100);

        System.out.println(zerodhaService.listStocks());
        /*
        implement Zerodha

         - listStocks(): List<Stock>
         - buyStock(userId, stockId, quantity): Order
         - sellStock(userId, stockId, quantity): Order
         - getPortfolio(userId)
         - depositMoney(userId, amount): throws
         - withdrawMoney(userId, amount): throws
         */
    }
}

class ZerodhaService {

    private final UserService userService;
    private final StockService stockService;
    private final OrderService orderService;

    ZerodhaService(UserService userService, StockService stockService, OrderService orderService) {
        this.userService = userService;
        this.stockService = stockService;
        this.orderService = orderService;
    }

    StockOrder buyStock(int userId, int stockId, int quantity) {
        try {
            User user = userService.getUser(userId);
            Stock stock = stockService.getStock(stockId);
            Portfolio portfolio = userService.getPortfolio(userId);

            user.lock.lock();
            try {
                int orderAmount = stock.price * quantity;
                if (user.getAccountBalance() < orderAmount) {
                    throw new RuntimeException("insufficient balance");
                }
                userService.withdrawFromUserAccount(userId, orderAmount);
                portfolio.addStockHolding(stockId, quantity);
            } finally {
                user.lock.unlock();
            }
            return orderService.createStockOrder(
                    stockId, StockOrderType.BUY, userId, quantity, OrderStatus.COMPLETED);
        } catch (Exception exception) {
            // This will be a custom exception we will catch
            return orderService.createStockOrder(
                    stockId, StockOrderType.BUY, userId, quantity, OrderStatus.FAILED);
        }
    }

    StockOrder sellStock(int userId, int stockId, int quantity) {
        try {
            Stock stock = stockService.getStock(stockId);
            User user = userService.getUser(userId);
            Portfolio portfolio = userService.getPortfolio(userId);

            user.lock.lock();
            try {
                portfolio.removeStockHolding(stockId, quantity);
                userService.depositToUserAccount(userId, stock.price * quantity);
            } finally {
                user.lock.unlock();
            }
            return orderService.createStockOrder(
                    stockId, StockOrderType.SELL, userId, quantity, OrderStatus.COMPLETED);
        } catch (Exception exception) {
            // This will be a custom exception we will catch
            return orderService.createStockOrder(
                    stockId, StockOrderType.SELL, userId, quantity, OrderStatus.FAILED);
        }
    }

    BankingOrder depositMoney(int userId, int amount) {
        try {
            userService.depositToUserAccount(userId, amount);
            return orderService.createBankingOrder(
                    userId, amount, BankingOrderType.DEPOSIT, OrderStatus.COMPLETED);
        } catch (Exception e) {
            System.out.println(e);
            return orderService.createBankingOrder(
                    userId, amount, BankingOrderType.DEPOSIT, OrderStatus.FAILED);
        }
    }

    BankingOrder withdrawMoney(int userId, int amount) {
        try {
            userService.withdrawFromUserAccount(userId, amount);
            return orderService.createBankingOrder(
                    userId, amount, BankingOrderType.WITHDRAW, OrderStatus.COMPLETED);
        } catch (Exception e) {
            System.out.println(e);
            return orderService.createBankingOrder(
                    userId, amount, BankingOrderType.WITHDRAW, OrderStatus.FAILED);
        }
    }

    List<Stock> listStocks() {
        return stockService.listStocks();
    }

    Portfolio getPortfolio(int userId) {
        return userService.getPortfolio(userId);
    }
}

class OrderService {
    private final BankingOrderDao bankingOrderDao;
    private final StockOrderDao stockOrderDao;

    OrderService(BankingOrderDao bankingOrderDao, StockOrderDao stockOrderDao) {
        this.bankingOrderDao = bankingOrderDao;
        this.stockOrderDao = stockOrderDao;
    }

    public StockOrder createStockOrder(
            int stockId, StockOrderType buy, int userId, int quantity, OrderStatus orderStatus) {
        StockOrder buyOrder = new StockOrder();
        buyOrder.orderStatus = orderStatus;
        buyOrder.stockId = stockId;
        buyOrder.orderType = buy;
        buyOrder.userId = userId;
        buyOrder.quantity = quantity;
        stockOrderDao.save(buyOrder);
        return buyOrder;
    }

    public BankingOrder createBankingOrder(
            int userId, int amount, BankingOrderType type, OrderStatus orderStatus) {
        BankingOrder order = new BankingOrder();
        order.amount = amount;
        order.orderStatus = orderStatus;
        order.userId = userId;
        order.orderType = type;
        bankingOrderDao.save(order);
        return order;
    }
}

interface BankingOrderDao {
    void save(BankingOrder bankingOrder);

    BankingOrder get(int bankingOrderId);
}

class InMemoryBankingOrderDao implements BankingOrderDao {
    Map<Integer, BankingOrder> orders = new ConcurrentHashMap<>();

    @Override
    public void save(BankingOrder bankingOrder) {
        orders.put(bankingOrder.id, bankingOrder);
    }

    @Override
    public BankingOrder get(int bankingOrderId) {
        return orders.get(bankingOrderId);
    }
}

interface StockOrderDao {
    void save(StockOrder stockOrder);

    StockOrder get(int bankingOrderId);
}

class InMemoryStockOrderDao implements StockOrderDao {
    Map<Integer, StockOrder> orders = new ConcurrentHashMap<>();

    @Override
    public void save(StockOrder stockOrder) {
        orders.put(stockOrder.id, stockOrder);
    }

    @Override
    public StockOrder get(int stockOrderId) {
        return orders.get(stockOrderId);
    }
}

class StockService {
    private final StockDao stockDao;

    StockService(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    List<Stock> listStocks() {
        return stockDao.listStocks();
    }

    Stock getStock(int stockId) {
        return stockDao.get(stockId);
    }

    void save(Stock stock) {
        stockDao.save(stock);
    }
}

interface StockDao {
    Stock get(int stockId);

    List<Stock> listStocks();

    void save(Stock stock);
}

class InMemoryStockDao implements StockDao {
    Map<Integer, Stock> stocks = new ConcurrentHashMap<>();

    @Override
    public Stock get(int stockId) {
        if (!stocks.containsKey(stockId)) throw new RuntimeException("stock doesnt exist");
        return stocks.get(stockId);
    }

    @Override
    public List<Stock> listStocks() {
        return stocks.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void save(Stock stock) {
        stocks.put(stock.id, stock);
    }
}

class UserService {
    private final UserDao userDao;
    private final PortfolioDao portfolioDao;

    UserService(UserDao userDao, PortfolioDao portfolioDao) {
        this.userDao = userDao;
        this.portfolioDao = portfolioDao;
    }

    User getUser(int userId) {
        return userDao.get(userId);
    }

    void save(User user) {
        userDao.save(user);
        portfolioDao.save(new Portfolio(user.id));
    }

    Portfolio getPortfolio(int userId) {
        return portfolioDao.get(userId);
    }

    void depositToUserAccount(int userId, int amount) {
        if (amount <= 0) throw new RuntimeException("invalid amount");
        User user = getUser(userId);
        user.lock.lock();
        try {
            user.accountBalance = user.accountBalance + amount;
            userDao.save(user);
        } finally {
            user.lock.unlock();
        }
    }

    void withdrawFromUserAccount(int userId, int amount) {
        if (amount <= 0) throw new RuntimeException("invalid amount");
        User user = getUser(userId);
        user.lock.lock();
        try {
            if (amount > user.getAccountBalance()) throw new RuntimeException("insufficient funds");
            user.accountBalance = user.accountBalance - amount;
            userDao.save(user);
        } finally {
            user.lock.unlock();
        }
    }
}

interface PortfolioDao {
    Portfolio get(int userId);

    void save(Portfolio portfolio);
}

class InMemoryPortfolio implements PortfolioDao {
    Map<Integer, Portfolio> portfolios = new ConcurrentHashMap<>();

    @Override
    public Portfolio get(int userId) {
        if (!portfolios.containsKey(userId)) throw new RuntimeException("postfolio doesnt exist");
        return portfolios.get(userId);
    }

    @Override
    public void save(Portfolio portfolio) {
        portfolios.put(portfolio.userId, portfolio);
    }
}

interface UserDao {
    User get(int userId);

    List<User> listUsers();

    void save(User user);
}

class InMemoryUserDao implements UserDao {
    Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public User get(int userId) {
        if (!users.containsKey(userId)) throw new RuntimeException("user doesnt exist");
        return users.get(userId);
    }

    @Override
    public List<User> listUsers() {
        return users.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        users.put(user.id, user);
    }
}

class Stock {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    String name;
    int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
        this.id = counter.getAndIncrement();
    }
}

class User {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    String name;
    int accountBalance;
    ReentrantLock lock = new ReentrantLock();

    int getAccountBalance() {
        return accountBalance;
    }

    public User(String name) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.accountBalance = 0;
        lock = new ReentrantLock();
    }
}

class Portfolio {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int userId;
    Map<Integer, Integer> stockHoldings = new HashMap<>();

    void addStockHolding(int stockId, int quantity) {
        if (quantity <= 0) throw new RuntimeException("invalid quantity");
        stockHoldings.put(stockId, stockHoldings.getOrDefault(stockId, 0) + quantity);
    }

    void removeStockHolding(int stockId, int quantity) {
        if (quantity <= 0) throw new RuntimeException("invalid quantity");
        if (!stockHoldings.containsKey(stockId))
            throw new RuntimeException("stock does not exist in portfolio");
        if (quantity > stockHoldings.get(stockId))
            throw new RuntimeException("not enough stocks to sell");
        stockHoldings.put(stockId, stockHoldings.getOrDefault(stockId, 0) - quantity);
    }

    public Portfolio(int userId) {
        this.userId = userId;
        this.id = counter.getAndIncrement();
    }
}

class Order {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int userId;
    OrderStatus orderStatus;
    public Instant submittedTime;

    public Order() {
        this.id = counter.getAndIncrement();
        this.submittedTime = Instant.now();
    }
}

enum StockOrderType {
    BUY,
    SELL
}

enum OrderStatus {
    PENDING,
    COMPLETED,
    FAILED
}

class BankingOrder extends Order {
    int amount;
    BankingOrderType orderType;
}

enum BankingOrderType {
    DEPOSIT,
    WITHDRAW
}

class StockOrder extends Order {
    int stockId;
    int quantity;
    StockOrderType orderType;
}
