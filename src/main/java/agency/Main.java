package agency;

import agency.model.User;
import agency.service.AuthService;
import agency.service.MenuService;
import agency.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        AuthService authService = new AuthService(userService);
        MenuService menuService = new MenuService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Добро пожаловать в Туристическое Агентство ===");

        User currentUser = null;

        while (currentUser == null) {
            System.out.print("Введите логин: ");
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            currentUser = authService.authenticate(login, password);
            if (currentUser == null) {
                System.out.println("Неверный логин или пароль, попробуйте снова.");
            }
        }

        boolean exit = false;
        while (!exit) {
            exit = menuService.showMenu(currentUser);
        }

        System.out.println("Выход из программы. До свидания!");
    }
}


