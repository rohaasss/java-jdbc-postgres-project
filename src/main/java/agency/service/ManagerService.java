package agency.service;

import java.sql.*;
import java.util.Scanner;

public class ManagerService {

    private static final String URL = "jdbc:postgresql://localhost:5432/tour_agency_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2020";

    public void showClientsByZone() {
        String sql = "SELECT zone_name, client_count FROM coverage_zones";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Клиенты по зонам:");
            while (rs.next()) {
                System.out.printf("- %s: %d клиентов%n", rs.getString("zone_name"), rs.getInt("client_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Можно добавить другие методы, например, для работы с клиентами, заказами и т.п.
}
