package agency.service;

import agency.model.User;

import java.util.Scanner;

public class MenuService {
    private final Scanner scanner = new Scanner(System.in);

    public boolean showMenu(User user) {
        String role = user.getRole().toLowerCase();
        System.out.println("\nДобро пожаловать, " + user.getLogin() + "! Роль: " + user.getRole());

        switch (role) {
            case "director": return directorMenu();
            case "marketing": return marketingMenu();
            case "manager": return managerMenu();
            case "worker": return workerMenu();
            case "sale manager": return salesManagerMenu();
            case "hr": return hrMenu();
            default:
                System.out.println("Неизвестная роль: " + user.getRole());
                return true; // выйти
        }
    }

    private boolean directorMenu() {
        System.out.println("Меню Директора:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для Директора");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }

    private boolean marketingMenu() {
        System.out.println("Меню Маркетолога:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для Маркетолога");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }

    private boolean managerMenu() {
        System.out.println("Меню Менеджера:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для Менеджера");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }

    private boolean workerMenu() {
        System.out.println("Меню Сотрудника:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для Сотрудника");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }

    private boolean salesManagerMenu() {
        System.out.println("Меню Агента по продажам:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для Агента по продажам");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }

    private boolean hrMenu() {
        System.out.println("Меню HR:\n1. Действие 1\n2. Выход");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Вы выбрали действие 1 для HR");
            return false;
        } else if ("2".equals(choice)) {
            return true;
        }
        System.out.println("Неверный выбор");
        return false;
    }
}
