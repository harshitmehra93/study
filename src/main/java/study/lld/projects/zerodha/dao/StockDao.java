package study.lld.projects.zerodha.dao;

import java.util.List;
import study.lld.projects.zerodha.model.Stock;

public interface StockDao {
    Stock get(int stockId);

    List<Stock> listStocks();

    void save(Stock stock);
}
