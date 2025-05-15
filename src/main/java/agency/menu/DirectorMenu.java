package agency.menu;

import agency.model.User;
import agency.service.DirectorService;

import java.util.Scanner;

public class DirectorMenu {

    private Scanner scanner;
    private DirectorService directorService;

    public DirectorMenu(Scanner scanner) {
        this.scanner = scanner;
        this.directorService = new DirectorService();
    }

    public void start(User user) {
        System.out.println("Приветствую дорогой, Директор!");
        while (true) {
            System.out.println("\nПожалуйста наберите номер меню, если хотите выйти, наберите 8:");
            System.out.println("1. Показать список всех зон покрытия");
            System.out.println("2. Показать зарплаты сотрудников");
            System.out.println("6. Повысить зарплату сотруднику");
            System.out.println("7. Понизить зарплату сотруднику");
            System.out.println("8. Выход");

            System.out.print("Выбор меню: >>> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    directorService.showCoverageZones();
                    break;
                case "2":
                    directorService.showEmployeeSalaries();
                    break;
                case "6":
                    System.out.print("Введите имя сотрудника для повышения зарплаты: >>> ");
                    String empUp = scanner.nextLine();
                    System.out.print("Введите сумму надбавки: >>> ");
                    double addAmount = Double.parseDouble(scanner.nextLine());
                    directorService.changeSalary(empUp, addAmount);
                    break;
                case "7":
                    System.out.print("Введите имя сотрудника для понижения зарплаты: >>> ");
                    String empDown = scanner.nextLine();
                    System.out.print("Введите сумму урезки: >>> ");
                    double subAmount = Double.parseDouble(scanner.nextLine());
                    directorService.changeSalary(empDown, -subAmount);
                    break;
                case "8":
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }
}
