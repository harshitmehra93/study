package study.lld.projects.zerodha.dao;

import study.lld.projects.zerodha.model.StockOrder;

public interface StockOrderDao {
    void save(StockOrder stockOrder);

    StockOrder get(int bankingOrderId);
}
