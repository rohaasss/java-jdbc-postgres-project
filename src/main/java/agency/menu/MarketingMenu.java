package agency.menu;

import agency.model.User;
import agency.service.MarketingService;

import java.util.Scanner;

public class MarketingMenu {

    private Scanner scanner;
    private MarketingService marketingService;

    public MarketingMenu(Scanner scanner) {
        this.scanner = scanner;
        this.marketingService = new MarketingService();
    }

    public void start(User user) {
        System.out.println("Приветствую, Маркетолог!");
        while (true) {
            System.out.println("\nПожалуйста, выберите пункт меню, для выхода наберите 4:");
            System.out.println("1. Показать маркетинговые зоны и бюджеты");
            System.out.println("2. Показать текущие средства для маркетинга");
            System.out.println("4. Выход");

            System.out.print("Выбор меню: >>> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    marketingService.showMarketingZones();
                    break;
                case "2":
                    marketingService.showCurrentMarketingFunds();
                    break;
                case "4":
                    System.out.println("Программа завершена, будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}
