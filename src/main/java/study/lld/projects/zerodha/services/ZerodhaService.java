package study.lld.projects.zerodha.services;

import java.util.List;
import study.lld.projects.zerodha.model.*;

public class ZerodhaService {

    private final UserService userService;
    private final StockService stockService;
    private final OrderService orderService;

    public ZerodhaService(
            UserService userService, StockService stockService, OrderService orderService) {
        this.userService = userService;
        this.stockService = stockService;
        this.orderService = orderService;
    }

    public StockOrder buyStock(int userId, int stockId, int quantity) {
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
                    stockId,
                    StockOrder.StockOrderType.BUY,
                    userId,
                    quantity,
                    Order.OrderStatus.COMPLETED);
        } catch (Exception exception) {
            // This will be a custom exception we will catch
            return orderService.createStockOrder(
                    stockId,
                    StockOrder.StockOrderType.BUY,
                    userId,
                    quantity,
                    Order.OrderStatus.FAILED);
        }
    }

    public StockOrder sellStock(int userId, int stockId, int quantity) {
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
                    stockId,
                    StockOrder.StockOrderType.SELL,
                    userId,
                    quantity,
                    Order.OrderStatus.COMPLETED);
        } catch (Exception exception) {
            // This will be a custom exception we will catch
            return orderService.createStockOrder(
                    stockId,
                    StockOrder.StockOrderType.SELL,
                    userId,
                    quantity,
                    Order.OrderStatus.FAILED);
        }
    }

    public BankingOrder depositMoney(int userId, int amount) {
        try {
            userService.depositToUserAccount(userId, amount);
            return orderService.createBankingOrder(
                    userId,
                    amount,
                    BankingOrder.BankingOrderType.DEPOSIT,
                    Order.OrderStatus.COMPLETED);
        } catch (Exception e) {
            System.out.println(e);
            return orderService.createBankingOrder(
                    userId,
                    amount,
                    BankingOrder.BankingOrderType.DEPOSIT,
                    Order.OrderStatus.FAILED);
        }
    }

    BankingOrder withdrawMoney(int userId, int amount) {
        try {
            userService.withdrawFromUserAccount(userId, amount);
            return orderService.createBankingOrder(
                    userId,
                    amount,
                    BankingOrder.BankingOrderType.WITHDRAW,
                    Order.OrderStatus.COMPLETED);
        } catch (Exception e) {
            System.out.println(e);
            return orderService.createBankingOrder(
                    userId,
                    amount,
                    BankingOrder.BankingOrderType.WITHDRAW,
                    Order.OrderStatus.FAILED);
        }
    }

    public List<Stock> listStocks() {
        return stockService.listStocks();
    }

    Portfolio getPortfolio(int userId) {
        return userService.getPortfolio(userId);
    }
}
