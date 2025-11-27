package leavesystem.service ;
import java.sql.*;
import leavesystem.util.DBConnection;
public class UserService {

    public boolean login(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public void register(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,password,role,ct_balance) VALUES(?,?,?,12)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "employee");
            ps.executeUpdate();
            System.out.println("✅ Employee Registered Successfully!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public String getRole(String username) {
        try (Connection con = DBConnection.getConnection()) {
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
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    public void showLeaveBalance(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT ct_balance FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            int allowedCT = 12;
            if (rs.next()) allowedCT = rs.getInt("ct_balance");

            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT leave_type, SUM(days) AS used FROM leaves WHERE user_id=? AND status='Approved' GROUP BY leave_type");
            ps2.setInt(1, userId);
            ResultSet r2 = ps2.executeQuery();

            int usedCT = 0, usedSL = 0, usedPL = 0;

            while (r2.next()) {
                String type = r2.getString("leave_type").toUpperCase();
                int used = r2.getInt("used");

                if (type.equals("CT")) usedCT = used;
                if (type.equals("SL")) usedSL = used;
                if (type.equals("PL")) usedPL = used;
            }

            int allowedSL = 6;
            int allowedPL = 10;

            System.out.println("\n=== YOUR LEAVE BALANCE ===\n");
            System.out.println("+------------+----------+--------+------------+");
            System.out.println("| Leave Type | Allowed  | Used   | Remaining  |");
            System.out.println("+------------+----------+--------+------------+");

            System.out.printf("| CT         | %-8d | %-6d | %-10d |\n", allowedCT, usedCT, (allowedCT - usedCT));
            System.out.printf("| SL         | %-8d | %-6d | %-10d |\n", allowedSL, usedSL, (allowedSL - usedSL));
            System.out.printf("| PL         | %-8d | %-6d | %-10d |\n", allowedPL, usedPL, (allowedPL - usedPL));

            System.out.println("+------------+----------+--------+------------+");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
