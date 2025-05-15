package agency.service;

import agency.model.Director;
import agency.model.Manager;
import agency.model.Worker;
import agency.model.Marketer;

import agency.model.User;
import java.sql.*;

public class UserService {

    private static final String URL = "jdbc:postgresql://localhost:5432/tour_agency_d";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2020";

    public boolean createUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(String username, String newPassword, String newRole) {
        String sql = "UPDATE users SET password = ?, role = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, newRole);
            stmt.setString(3, username);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Double getSalaryByLogin(String login) {
        String sql = "SELECT salary FROM employee_salaries WHERE employee_login = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // или 0.0, если хочешь по-другому
    }
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                String user = rs.getString("username");
                String pass = rs.getString("password");

                switch (role.toLowerCase()) {
                    case "director":
                        return new Director(id, user, pass);
                    case "manager":
                        return new Manager(id, user, pass);
                    case "worker":
                        return new Worker(id, user, pass);
                    case "marketing":
                        return new Marketer(id, user, pass);
                    default:
                        return new User(id, user, pass, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}

