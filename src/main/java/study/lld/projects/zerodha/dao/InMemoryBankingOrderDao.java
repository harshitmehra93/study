package study.lld.projects.zerodha.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import study.lld.projects.zerodha.model.BankingOrder;

public class InMemoryBankingOrderDao implements BankingOrderDao {
    Map<Integer, BankingOrder> orders = new ConcurrentHashMap<>();

    @Override
    public void save(BankingOrder bankingOrder) {
        orders.put(bankingOrder.id, bankingOrder);
    }

    @Override
    public BankingOrder get(int bankingOrderId) {
        return orders.get(bankingOrderId);
    }
}
