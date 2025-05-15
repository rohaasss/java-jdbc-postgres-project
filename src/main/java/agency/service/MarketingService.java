package agency.service;

import java.sql.*;
import java.util.Scanner;

public class MarketingService {

    private static final String URL = "jdbc:postgresql://localhost:5432/tour_agency_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2020";

    public void showMarketingZones() {
        String sql = "SELECT zone_name, marketing_budget FROM marketing_zones";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Маркетинговые зоны и бюджеты:");
            while (rs.next()) {
                System.out.printf("- %s: бюджет %.2f%n",
                        rs.getString("zone_name"),
                        rs.getDouble("marketing_budget"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showCurrentMarketingFunds() {
        String sql = "SELECT SUM(marketing_budget) AS total_budget FROM marketing_zones";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.printf("Текущие средства для маркетинга: %.2f%n", rs.getDouble("total_budget"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Можно добавить методы для обновления бюджета и др.
}
