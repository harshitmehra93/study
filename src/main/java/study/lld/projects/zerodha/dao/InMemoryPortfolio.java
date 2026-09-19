package study.lld.projects.zerodha.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import study.lld.projects.zerodha.model.Portfolio;

public class InMemoryPortfolio implements PortfolioDao {
    Map<Integer, Portfolio> portfolios = new ConcurrentHashMap<>();

    @Override
    public Portfolio get(int userId) {
        if (!portfolios.containsKey(userId)) throw new RuntimeException("postfolio doesnt exist");
        return portfolios.get(userId);
    }

    @Override
    public void save(Portfolio portfolio) {
        portfolios.put(portfolio.userId, portfolio);
    }
}
