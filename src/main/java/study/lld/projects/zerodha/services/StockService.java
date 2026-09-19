package study.lld.projects.zerodha.services;

import java.util.List;
import study.lld.projects.zerodha.dao.StockDao;
import study.lld.projects.zerodha.model.Stock;

public class StockService {
    private final StockDao stockDao;

    public StockService(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    List<Stock> listStocks() {
        return stockDao.listStocks();
    }

    Stock getStock(int stockId) {
        return stockDao.get(stockId);
    }

    public void save(Stock stock) {
        stockDao.save(stock);
    }
}
