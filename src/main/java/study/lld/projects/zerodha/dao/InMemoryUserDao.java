package study.lld.projects.zerodha.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import study.lld.projects.zerodha.model.User;

public class InMemoryUserDao implements UserDao {
    Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public User get(int userId) {
        if (!users.containsKey(userId)) throw new RuntimeException("user doesnt exist");
        return users.get(userId);
    }

    @Override
    public List<User> listUsers() {
        return users.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        users.put(user.id, user);
    }
}
