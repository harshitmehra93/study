package study.lld.projects.zerodha.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import study.lld.projects.zerodha.model.Stock;

public class InMemoryStockDao implements StockDao {
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
