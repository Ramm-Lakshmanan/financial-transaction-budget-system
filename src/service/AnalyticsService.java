package service;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnalyticsService {

    public static void categorySummary() {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT c.category_name, SUM(t.amount) total " +
                            "FROM transactions t JOIN categories c ON t.category_id=c.category_id " +
                            "WHERE t.type='EXPENSE' GROUP BY c.category_name";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getString("category_name") +
                                " → ₹" +
                                rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void transactionHistory(int accountId) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT transaction_id, amount, type, description, transaction_date " +
                            "FROM transactions WHERE account_id=? ORDER BY transaction_date DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("transaction_id") + " | " +
                                rs.getString("type") + " | ₹" +
                                rs.getDouble("amount") + " | " +
                                rs.getString("description") + " | " +
                                rs.getDate("transaction_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void categoryTransactions(int categoryId) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT amount, description, transaction_date " +
                            "FROM transactions WHERE category_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        "₹" + rs.getDouble("amount") +
                                " | " + rs.getString("description") +
                                " | " + rs.getDate("transaction_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void transactionsByDate(String startDate, String endDate) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT amount, description, transaction_date " +
                            "FROM transactions WHERE transaction_date BETWEEN ? AND ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, startDate);
            ps.setString(2, endDate);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        "₹" + rs.getDouble("amount") +
                                " | " + rs.getString("description") +
                                " | " + rs.getDate("transaction_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void monthlySummary() {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT MONTH(transaction_date) m, SUM(amount) total " +
                            "FROM transactions WHERE type='EXPENSE' " +
                            "GROUP BY m";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        "Month " + rs.getInt("m") +
                                " → ₹" + rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void categoryBreakdown() {

        try (Connection conn = DBConnection.getConnection()) {

            String sql =
                    "SELECT c.category_name, SUM(t.amount) total " +
                            "FROM transactions t " +
                            "JOIN categories c ON t.category_id=c.category_id " +
                            "WHERE t.type='EXPENSE' " +
                            "GROUP BY c.category_name";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getString("category_name") +
                                " → ₹" +
                                rs.getDouble("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}