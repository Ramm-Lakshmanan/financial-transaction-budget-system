package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BudgetService {

    public static void checkBudget(Connection conn, int userId, int categoryId) throws Exception {

        String budgetQuery =
                "SELECT monthly_limit FROM budgets WHERE user_id=? AND category_id=? AND month=MONTH(CURDATE()) AND year=YEAR(CURDATE())";

        PreparedStatement ps = conn.prepareStatement(budgetQuery);
        ps.setInt(1, userId);
        ps.setInt(2, categoryId);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return;

        double limit = rs.getDouble("monthly_limit");

        String spentQuery =
                "SELECT SUM(amount) total FROM transactions WHERE category_id=? AND type='EXPENSE' AND MONTH(transaction_date)=MONTH(CURDATE())";

        PreparedStatement ps2 = conn.prepareStatement(spentQuery);
        ps2.setInt(1, categoryId);

        ResultSet rs2 = ps2.executeQuery();

        if (rs2.next()) {

            double spent = rs2.getDouble("total");

            if (spent > limit) {
                System.out.println("⚠ Budget exceeded for this category!");
            }
        }
    }
}