package study.lld.projects.zerodha.dao;

import study.lld.projects.zerodha.model.Portfolio;

public interface PortfolioDao {
    Portfolio get(int userId);

    void save(Portfolio portfolio);
}
