package service;

import db.DBConnection;
import util.AuditLogger;

import java.sql.*;

public class TransactionService {

    public static void addTransaction(
            int userId,
            int accountId,
            int categoryId,
            double amount,
            String type,
            String description
    ) {

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            double balance = getBalance(conn, accountId);

            if (type.equals("EXPENSE") && balance < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            String sql =
                    "INSERT INTO transactions(account_id, category_id, amount, type, description) " +
                            "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, accountId);
            ps.setInt(2, categoryId);
            ps.setDouble(3, amount);
            ps.setString(4, type);
            ps.setString(5, description);

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            keys.next();

            int transactionId = keys.getInt(1);

            updateBalance(conn, accountId, amount, type);

            AuditLogger.log(conn, userId, "CREATE", "TRANSACTION", transactionId);

            BudgetService.checkBudget(conn, userId, categoryId);

            conn.commit();

            System.out.println("Transaction successful");

        } catch (Exception e) {

            System.out.println("Transaction failed → rollback");
            e.printStackTrace();
        }
    }

    private static double getBalance(Connection conn, int accountId) throws Exception {

        String sql = "SELECT balance FROM accounts WHERE account_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) throw new RuntimeException("Account not found");

        return rs.getDouble("balance");
    }

    private static void updateBalance(Connection conn, int accountId, double amount, String type) throws Exception {

        String sql;

        if (type.equals("EXPENSE"))
            sql = "UPDATE accounts SET balance = balance - ? WHERE account_id=?";
        else
            sql = "UPDATE accounts SET balance = balance + ? WHERE account_id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, amount);
        ps.setInt(2, accountId);

        ps.executeUpdate();
    }
}