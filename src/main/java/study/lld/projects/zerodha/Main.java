package study.lld.projects.zerodha;

import study.lld.projects.zerodha.dao.*;
import study.lld.projects.zerodha.model.Stock;
import study.lld.projects.zerodha.model.User;
import study.lld.projects.zerodha.services.OrderService;
import study.lld.projects.zerodha.services.StockService;
import study.lld.projects.zerodha.services.UserService;
import study.lld.projects.zerodha.services.ZerodhaService;

/*
*
Learnings
1. concurrent hash map for thread safety
2. keeping synchronisation and thread safety at an intentional level
3. rollback needs to be discussed
4. taking multiple locks is ok but can cause deadlocks, try to take minimum locks. unlock in the reverse order.
* */
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
