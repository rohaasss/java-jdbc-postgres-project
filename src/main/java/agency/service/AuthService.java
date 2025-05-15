package agency.service;

import agency.model.User;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User authenticate(String login, String password) {
        User user = userService.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
