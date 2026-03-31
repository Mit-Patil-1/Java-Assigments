package com.mycompany.assignment2.dao;

import com.mycompany.assignment2.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsDAO {

    public void logSession(String sessionId, String userAgent, String ip) {
        // Simple check to avoid duplicates for same session if we only want unique visits
        // But requirement says "records all session ID"
        String query = "INSERT INTO session_tracking (session_id, user_agent, ip_address) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, sessionId);
            ps.setString(2, userAgent);
            ps.setString(3, ip);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Clients who have visited more than 5 times
    // This assumes we can track 'clients' maybe by IP or if logged in by user_id? 
    // Requirement is vague ("Clients"), but "visited" implies session tracking.
    // If we track by IP as proxy for client:
    public List<Map<String, Object>> getFrequentVisitors() {
        List<Map<String, Object>> list = new ArrayList<>();
        String query = "SELECT ip_address, COUNT(*) as visit_count FROM session_tracking GROUP BY ip_address HAVING visit_count > 5";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("ip_address", rs.getString("ip_address"));
                map.put("visit_count", rs.getInt("visit_count"));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
