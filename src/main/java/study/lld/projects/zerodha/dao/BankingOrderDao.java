package study.lld.projects.zerodha.dao;

import study.lld.projects.zerodha.model.BankingOrder;

public interface BankingOrderDao {
    void save(BankingOrder bankingOrder);

    BankingOrder get(int bankingOrderId);
}
