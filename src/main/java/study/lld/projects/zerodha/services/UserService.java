package study.lld.projects.zerodha.services;

import study.lld.projects.zerodha.dao.PortfolioDao;
import study.lld.projects.zerodha.dao.UserDao;
import study.lld.projects.zerodha.model.Portfolio;
import study.lld.projects.zerodha.model.User;

public class UserService {
    private final UserDao userDao;
    private final PortfolioDao portfolioDao;

    public UserService(UserDao userDao, PortfolioDao portfolioDao) {
        this.userDao = userDao;
        this.portfolioDao = portfolioDao;
    }

    User getUser(int userId) {
        return userDao.get(userId);
    }

    public void save(User user) {
        userDao.save(user);
        portfolioDao.save(new Portfolio(user.id));
    }

    Portfolio getPortfolio(int userId) {
        return portfolioDao.get(userId);
    }

    public void depositToUserAccount(int userId, int amount) {
        if (amount <= 0) throw new RuntimeException("invalid amount");
        User user = getUser(userId);
        user.lock.lock();
        try {
            user.accountBalance = user.accountBalance + amount;
            userDao.save(user);
        } finally {
            user.lock.unlock();
        }
    }

    public void withdrawFromUserAccount(int userId, int amount) {
        if (amount <= 0) throw new RuntimeException("invalid amount");
        User user = getUser(userId);
        user.lock.lock();
        try {
            if (amount > user.getAccountBalance()) throw new RuntimeException("insufficient funds");
            user.accountBalance = user.accountBalance - amount;
            userDao.save(user);
        } finally {
            user.lock.unlock();
        }
    }
}
