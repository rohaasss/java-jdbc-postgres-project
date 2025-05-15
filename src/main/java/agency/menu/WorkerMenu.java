package agency.menu;

import agency.model.User;
import agency.service.UserService;
import agency.service.WorkerService;

import java.util.List;
import java.util.Scanner;

public class WorkerMenu {

    private Scanner scanner;
    private WorkerService workerService = new WorkerService();
    private UserService userService = new UserService();

    public WorkerMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(User user) {
        System.out.println("Приветствую дорогой, Сотрудник " + user.getUsername() + "!");
        while (true) {
            System.out.println("Пожалуйста наберите номер меню для работы с программой, если закончили, то наберите 5:");
            System.out.println("1. Показать список порученных мне дел.");
            System.out.println("2. Выполнить поручение.");
            System.out.println("3. Показать список завершенных указаний.");
            System.out.println("4. Показать зарплату.");
            System.out.println("5. Выход.");

            System.out.print("Выбор меню: >>> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<String> tasks = workerService.getAssignedTasks(user.getUsername());
                    if (tasks.isEmpty()) {
                        System.out.println("Нет порученных дел.");
                    } else {
                        System.out.println("Порученные дела:");
                        tasks.forEach(System.out::println);
                    }
                    break;

                case "2":
                    System.out.print("Пожалуйста, напишите дело, которое вы собираетесь выполнить >>> ");
                    String task = scanner.nextLine();
                    boolean completed = workerService.completeTask(user.getUsername(), task);
                    if (completed) {
                        System.out.println("Дело успешно выполнено и перенесено в завершённые.");
                    } else {
                        System.out.println("Задача не найдена или ошибка выполнения.");
                    }
                    break;

                case "3":
                    List<String> completedTasks = workerService.getCompletedTasks(user.getUsername());
                    if (completedTasks.isEmpty()) {
                        System.out.println("Нет завершённых дел.");
                    } else {
                        System.out.println("Завершённые дела:");
                        completedTasks.forEach(System.out::println);
                    }
                    break;

                case "4":
                    Double salary = userService.getSalaryByLogin(user.getUsername());
                    if (salary != null) {
                        System.out.println("Ваша текущая зарплата: " + salary);
                    } else {
                        System.out.println("Информация о зарплате недоступна.");
                    }
                    break;

                case "5":
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;

                default:
                    System.out.println("Неверный ввод. Попробуйте ещё раз.");
            }
        }
    }
}
