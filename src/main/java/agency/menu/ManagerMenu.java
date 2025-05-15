package agency.menu;

import agency.model.User;
import agency.service.ManagerService;

import java.util.Scanner;

public class ManagerMenu {

    private Scanner scanner;
    private ManagerService managerService;

    public ManagerMenu(Scanner scanner) {
        this.scanner = scanner;
        this.managerService = new ManagerService();
    }

    public void start(User user) {
        System.out.println("Приветствую, Менеджер!");
        while (true) {
            System.out.println("\nВыберите пункт меню, для выхода наберите 3:");
            System.out.println("1. Показать клиентов по зонам");
            System.out.println("3. Выход");

            System.out.print("Выбор меню: >>> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    managerService.showClientsByZone();
                    break;
                case "3":
                    System.out.println("Программа завершена, будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
