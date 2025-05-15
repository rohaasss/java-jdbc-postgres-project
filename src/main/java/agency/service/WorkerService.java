package agency.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerService {

    private static final String URL = "jdbc:postgresql://localhost:5432/tour_agency_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2020";

    public List<String> getAssignedTasks(String login) {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT description FROM tasks WHERE employee_login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tasks.add(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<String> getCompletedTasks(String login) {
        List<String> completedTasks = new ArrayList<>();
        String sql = "SELECT description FROM completed_tasks WHERE employee_login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                completedTasks.add(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completedTasks;
    }

    public boolean completeTask(String login, String taskDescription) {
        String deleteSql = "DELETE FROM tasks WHERE employee_login = ? AND description = ?";
        String insertSql = "INSERT INTO completed_tasks (employee_login, description) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                 PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                deleteStmt.setString(1, login);
                deleteStmt.setString(2, taskDescription);
                int deleted = deleteStmt.executeUpdate();

                if (deleted == 0) {
                    conn.rollback();
                    return false; // задачи не было
                }

                insertStmt.setString(1, login);
                insertStmt.setString(2, taskDescription);
                insertStmt.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
