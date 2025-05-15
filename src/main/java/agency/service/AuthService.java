package agency.service;

import agency.model.User;

import java.util.Scanner;

public class AuthService {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();

    public User authenticate(String expectedRole) {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = userService.login(login, password);


        if (user != null && user.getRole().equalsIgnoreCase(expectedRole)) {
            return user;
        }

        System.out.println("Ошибка авторизации: неправильные логин, пароль или роль.");
        return null;
    }
}
