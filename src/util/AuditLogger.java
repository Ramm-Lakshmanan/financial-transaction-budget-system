package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuditLogger {

    public static void log(Connection conn, int userId, String action, String entity, int entityId) throws Exception {

        String sql = "INSERT INTO audit_logs(user_id, action_type, entity_type, entity_id) VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, action);
        ps.setString(3, entity);
        ps.setInt(4, entityId);

        ps.executeUpdate();
    }
}