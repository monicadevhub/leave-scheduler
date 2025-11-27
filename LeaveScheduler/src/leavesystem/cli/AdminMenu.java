package leavesystem.cli;

import java.util.Scanner;
import leavesystem.service.LeaveService;

public class AdminMenu {

    private Scanner sc = new Scanner(System.in);
    private LeaveService ls = new LeaveService();

    public void menu() {

        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View All Leave Requests");
            System.out.println("2. Approve / Reject Leave");
            System.out.println("3. Logout");
            System.out.print("Choice: ");

            int c;
            try { c = Integer.parseInt(sc.nextLine()); }
            catch (Exception ex) { System.out.println("❌ Invalid Input!"); continue; }

            if (c == 1) {
                ls.viewAllLeaves();
            }
            else if (c == 2) {
                System.out.print("Enter Leave ID: ");
                int id;
                try { id = Integer.parseInt(sc.nextLine()); } catch (Exception ex) { System.out.println("❌ Invalid ID"); continue; }
                System.out.print("Enter Status (Approved/Rejected): ");
                String st = sc.nextLine();
                ls.updateLeave(id, st);
            }
            else if (c == 3) {
                System.out.println("👋 Admin Logged Out!");
                break;
            }
        }
    }
}
