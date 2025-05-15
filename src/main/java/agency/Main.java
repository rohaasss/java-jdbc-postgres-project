package agency;

import agency.service.MenuService;
import agency.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        MenuService menuService = new MenuService(scanner, userService);

        menuService.start();
    }
}
