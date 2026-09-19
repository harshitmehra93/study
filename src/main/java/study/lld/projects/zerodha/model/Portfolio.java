package study.lld.projects.zerodha.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Portfolio {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    public int userId;
    Map<Integer, Integer> stockHoldings = new HashMap<>();

    public void addStockHolding(int stockId, int quantity) {
        if (quantity <= 0) throw new RuntimeException("invalid quantity");
        stockHoldings.put(stockId, stockHoldings.getOrDefault(stockId, 0) + quantity);
    }

    public void removeStockHolding(int stockId, int quantity) {
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
