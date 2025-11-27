package leavesystem.dao;

import leavesystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean login(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean register(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password,role,ct_balance) VALUES(?,?,?,12)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "employee");
            ps.executeUpdate();
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public String getRole(String username) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return "employee";
            PreparedStatement ps = con.prepareStatement(
                "SELECT role FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("role");
        } catch (Exception e) { e.printStackTrace(); }
        return "employee";
    }

    public int getUserId(String username) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return -1;
            PreparedStatement ps = con.prepareStatement(
                "SELECT id FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    public int getCTBalance(int userId) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return 12;
            PreparedStatement ps = con.prepareStatement(
                "SELECT ct_balance FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("ct_balance");
        } catch (Exception e) { e.printStackTrace(); }
        return 12;
    }
}
