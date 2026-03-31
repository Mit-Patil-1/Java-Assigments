package com.mycompany.assignment2.dao;

import com.mycompany.assignment2.model.Order;
import com.mycompany.assignment2.model.OrderDetail;
import com.mycompany.assignment2.util.DBConnection;
import java.sql.*;
import java.util.List;

public class OrderDAO {
    
    public int createOrder(Order order, List<OrderDetail> details) {
        String orderQuery = "INSERT INTO order_master (session_id, user_id, payment_mode, tax, total_amount, order_status) VALUES (?, ?, ?, ?, ?, ?)";
        String detailQuery = "INSERT INTO order_details (order_id, product_id, product_price, discount, quantity) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            
            int orderId = -1;
            try (PreparedStatement ps = con.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getSessionId());
                if (order.getUserId() > 0) ps.setInt(2, order.getUserId()); else ps.setNull(2, java.sql.Types.INTEGER);
                ps.setString(3, order.getPaymentMode());
                ps.setDouble(4, order.getTax());
                ps.setDouble(5, order.getTotalAmount());
                ps.setString(6, order.getOrderStatus());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) orderId = rs.getInt(1);
            }
            
            if (orderId != -1) {
                try (PreparedStatement ps = con.prepareStatement(detailQuery)) {
                    for (OrderDetail detail : details) {
                        ps.setInt(1, orderId);
                        ps.setInt(2, detail.getProductId());
                        ps.setDouble(3, detail.getProductPrice());
                        ps.setDouble(4, detail.getDiscount());
                        ps.setInt(5, detail.getQuantity());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
            }
            
            con.commit();
            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
            if(con != null) {
                try { con.rollback(); } catch(SQLException ex) { ex.printStackTrace(); }
            }
            return -1;
        } finally {
            if(con != null) {
                try { con.setAutoCommit(true); con.close(); } catch(SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
