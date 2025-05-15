package agency.service;

import java.sql.*;
import java.util.Scanner;

public class DirectorService {

    private static final String URL = "jdbc:postgresql://localhost:5432/tour_agency_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2020";

    public void showCoverageZones() {
        String sql = "SELECT zone_name, coverage_percent, client_count FROM coverage_zones";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Зоны покрытия:");
            while (rs.next()) {
                System.out.printf("- %s: %d%%, клиентов: %d%n",
                        rs.getString("zone_name"),
                        rs.getInt("coverage_percent"),
                        rs.getInt("client_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showEmployeeSalaries() {
        String sql = "SELECT employee_name, salary FROM employee_salaries";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Зарплаты сотрудников:");
            while (rs.next()) {
                System.out.printf("- %s: %.2f%n", rs.getString("employee_name"), rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeSalary(String employeeName, double amount) {
        String sql = "UPDATE employee_salaries SET salary = salary + ? WHERE employee_name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, employeeName);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Зарплата успешно обновлена.");
            } else {
                System.out.println("Сотрудник не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
