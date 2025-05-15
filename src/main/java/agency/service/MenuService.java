package agency.service;

import agency.model.User;
import agency.menu.MarketingMenu;
import agency.menu.ManagerMenu;
import agency.menu.WorkerMenu;
import agency.menu.DirectorMenu;

import java.util.Scanner;

public class MenuService {

    private Scanner scanner;
    private UserService userService;

    public MenuService(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void start() {
        System.out.print("Для запуска программы, пожалуйста введите тип аккаунта: >>> ");
        String accountType = scanner.nextLine().trim().toLowerCase();

        if (!accountType.equals("marketing") && !accountType.equals("manager") && !accountType.equals("director") && !accountType.equals("worker")) {
            System.out.println("Извините, но мы не нашли такой тип аккаунта, пожалуйста повторите.");
            return;
        }

        System.out.print("Введите логин: ");
        String login = scanner.nextLine().trim();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine().trim();

        User user = userService.login(login, password);
        if (user == null) {
            System.out.println("Ошибка авторизации. Проверьте логин и пароль.");
            return;
        }

        if (!user.getRole().equalsIgnoreCase(accountType)) {
            System.out.println("Роль пользователя не совпадает с введенным типом аккаунта.");
            return;
        }

        switch (accountType) {
            case "marketing":
                new MarketingMenu(scanner).start(user);
                break;
            case "manager":
                new ManagerMenu(scanner).start(user);
                break;
            case "director":
                new DirectorMenu(scanner).start(user);
                break;
            case "worker":
                new WorkerMenu(scanner).start(user);
                break;
            default:
                System.out.println("Неизвестный тип аккаунта.");
        }

        System.out.println("Программа завершена, мы будем рады вашему возвращению!");
    }
}
