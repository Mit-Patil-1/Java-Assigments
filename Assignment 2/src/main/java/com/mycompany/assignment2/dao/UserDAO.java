package com.mycompany.assignment2.dao;

import com.mycompany.assignment2.model.User;
import com.mycompany.assignment2.util.DBConnection;
import java.sql.*;

public class UserDAO {
    
    public boolean registerUser(User user) {
        String query = "INSERT INTO user_master (username, login_id, password, password_question, password_answer, email, phone, address, city, state, country, pin, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getLoginId());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPasswordQuestion());
            ps.setString(5, user.getPasswordAnswer());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPhone());
            ps.setString(8, user.getAddress());
            ps.setString(9, user.getCity());
            ps.setString(10, user.getState());
            ps.setString(11, user.getCountry());
            ps.setString(12, user.getPin());
            ps.setString(13, "CUSTOMER");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String loginId, String password) {
        String query = "SELECT * FROM user_master WHERE login_id = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, loginId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setLoginId(rs.getString("login_id"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setCountry(rs.getString("country"));
                user.setPin(rs.getString("pin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateUser(User user) {
        String query = "UPDATE user_master SET username=?, password_question=?, password_answer=?, email=?, phone=?, address=?, city=?, state=?, country=?, pin=? WHERE user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             ps.setString(1, user.getUsername());
             ps.setString(2, user.getPasswordQuestion());
             ps.setString(3, user.getPasswordAnswer());
             ps.setString(4, user.getEmail());
             ps.setString(5, user.getPhone());
             ps.setString(6, user.getAddress());
             ps.setString(7, user.getCity());
             ps.setString(8, user.getState());
             ps.setString(9, user.getCountry());
             ps.setString(10, user.getPin());
             ps.setInt(11, user.getUserId());
             return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getPassword(String loginId, String answer) {
        String query = "SELECT password FROM user_master WHERE login_id = ? AND password_answer = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, loginId);
            ps.setString(2, answer);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
