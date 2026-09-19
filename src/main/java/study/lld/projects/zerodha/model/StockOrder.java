package study.lld.projects.zerodha.model;

public class StockOrder extends Order {
    public int stockId;
    public int quantity;
    public StockOrderType orderType;

    public enum StockOrderType {
        BUY,
        SELL
    }
}
