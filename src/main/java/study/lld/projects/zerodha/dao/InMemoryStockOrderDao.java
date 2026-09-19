package study.lld.projects.zerodha.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import study.lld.projects.zerodha.model.StockOrder;

public class InMemoryStockOrderDao implements StockOrderDao {
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
