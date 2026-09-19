package study.lld.projects.zerodha.model;

public class BankingOrder extends Order {
    public int amount;
    public BankingOrderType orderType;

    public enum BankingOrderType {
        DEPOSIT,
        WITHDRAW
    }
}
