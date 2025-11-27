package leavesystem.dao;

import leavesystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LeaveDAO {

    public boolean applyLeave(int userId, String type, String start, String end, String reason, int days) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO leaves(user_id, start_date, end_date, days, reason, status, leave_type) VALUES(?,?,?,?,?,?,?)");
            ps.setInt(1, userId);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setInt(4, days);
            ps.setString(5, reason);
            ps.setString(6, "Pending");
            ps.setString(7, type);
            ps.executeUpdate();
            return true;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<String> viewMyLeaves(int userId) {
        List<String> rows = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return rows;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM leaves WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String row = rs.getInt("id") + " | " +
                        rs.getString("leave_type") + " | " +
                        rs.getString("start_date") + " to " + rs.getString("end_date") +
                        " | Days: " + rs.getInt("days") +
                        " | " + rs.getString("reason") +
                        " | " + rs.getString("status");
                rows.add(row);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return rows;
    }

    public List<String> viewAllLeaves() {
        List<String> rows = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return rows;
            ResultSet rs = con.prepareStatement(
                "SELECT l.*, u.username FROM leaves l JOIN users u ON l.user_id=u.id").executeQuery();
            while (rs.next()) {
                String row = rs.getInt("id") + " | " +
                        rs.getString("username") + " | " +
                        rs.getString("leave_type") + " | " +
                        rs.getString("start_date") + " to " +
                        rs.getString("end_date") + " | Days: " +
                        rs.getInt("days") + " | " +
                        rs.getString("reason") + " | " +
                        rs.getString("status");
                rows.add(row);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return rows;
    }

    public boolean updateLeave(int id, String status) {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return false;
            PreparedStatement ps = con.prepareStatement("UPDATE leaves SET status=? WHERE id=?");
            ps.setString(1, status);
            ps.setInt(2, id);
            int updated = ps.executeUpdate();
            return updated > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
