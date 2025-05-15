package agency.service;

import agency.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Предустановленные пользователи
        users.add(new User(1, "director1", "pass123", "director"));
        users.add(new User(2, "marketing1", "pass123", "marketing"));
        users.add(new User(3, "manager1", "pass123", "manager"));
        users.add(new User(4, "worker1", "pass123", "worker"));
        users.add(new User(5, "sale1", "pass123", "sale manager"));
        users.add(new User(6, "hr1", "pass123", "hr"));
    }

    public User findByLogin(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
