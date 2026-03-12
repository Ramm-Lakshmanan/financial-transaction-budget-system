package service;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

    public static void register(String username, String password) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "INSERT INTO users(username,password_hash) VALUES (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            System.out.println("User registered successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean login(String username, String password) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT user_id FROM users WHERE username=? AND password_hash=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            var rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Login successful");

                return true;
            }

            System.out.println("Invalid credentials");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}