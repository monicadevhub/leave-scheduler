 package leavesystem.service;
import java.sql.*;
import leavesystem.util.DBConnection;

public class LeaveService {

    public void applyLeave(int userId, String type, String start, String end, String reason, int days) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO leaves(user_id, start_date, end_date, days, reason, status, leave_type) VALUES(?,?,?,?,?,?,?)");

            ps.setInt(1, userId);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setInt(4, days);
            ps.setString(5, reason);
            ps.setString(6, "Pending");
            ps.setString(7, type.toUpperCase()); // FIXED HERE

            ps.executeUpdate();
            System.out.println("✅ Leave Applied Successfully!");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void viewMyLeaves(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM leaves WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n=== YOUR LEAVES ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("leave_type") + " | " +
                        rs.getString("start_date") + " to " + rs.getString("end_date") +
                        " | Days: " + rs.getInt("days") +
                        " | " + rs.getString("reason") +
                        " | " + rs.getString("status")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void viewAllLeaves() {
        try (Connection con = DBConnection.getConnection()) {

            ResultSet rs = con.prepareStatement(
                    "SELECT l.*, u.username FROM leaves l JOIN users u ON l.user_id=u.id")
                    .executeQuery();

            System.out.println("\n=== ALL LEAVE REQUESTS ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("username") + " | " +
                        rs.getString("leave_type") + " | " +
                        rs.getString("start_date") + " to " +
                        rs.getString("end_date") + " | Days: " +
                        rs.getInt("days") + " | " +
                        rs.getString("reason") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateLeave(int id, String status) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE leaves SET status=? WHERE id=?");
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

            System.out.println("✅ Leave Status Updated!");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
