package study.lld.projects.zerodha.services;

import study.lld.projects.zerodha.dao.BankingOrderDao;
import study.lld.projects.zerodha.dao.StockOrderDao;
import study.lld.projects.zerodha.model.*;

public class OrderService {
    private final BankingOrderDao bankingOrderDao;
    private final StockOrderDao stockOrderDao;

    public OrderService(BankingOrderDao bankingOrderDao, StockOrderDao stockOrderDao) {
        this.bankingOrderDao = bankingOrderDao;
        this.stockOrderDao = stockOrderDao;
    }

    public StockOrder createStockOrder(
            int stockId,
            StockOrder.StockOrderType buy,
            int userId,
            int quantity,
            Order.OrderStatus orderStatus) {
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
            int userId,
            int amount,
            BankingOrder.BankingOrderType type,
            Order.OrderStatus orderStatus) {
        BankingOrder order = new BankingOrder();
        order.amount = amount;
        order.orderStatus = orderStatus;
        order.userId = userId;
        order.orderType = type;
        bankingOrderDao.save(order);
        return order;
    }
}
