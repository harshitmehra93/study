package study.lld.projects.zerodha.dao;

import java.util.List;
import study.lld.projects.zerodha.model.User;

public interface UserDao {
    User get(int userId);

    List<User> listUsers();

    void save(User user);
}
