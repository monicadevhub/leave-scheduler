package leavesystem.cli;

import java.util.Scanner;
import leavesystem.service.LeaveService;
import leavesystem.service.UserService;

public class EmployeeMenu {

    private Scanner sc = new Scanner(System.in);
    private LeaveService ls = new LeaveService();
    private UserService us = new UserService();

    public void menu(int userId) {

        while (true) {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. Apply Leave");
            System.out.println("2. View My Leaves");
            System.out.println("3. View Leave Balance");
            System.out.println("4. Logout");
            System.out.print("Choice: ");

            int c;
            try { c = Integer.parseInt(sc.nextLine()); }
            catch (Exception ex) { System.out.println("❌ Invalid Input!"); continue; }

            if (c == 1) {
                System.out.println("Leave Types: CT, SL, PL");
                System.out.print("Enter Leave Type: ");
                String type = sc.nextLine();

                System.out.print("Start Date (yyyy-mm-dd): ");
                String s = sc.nextLine();
                System.out.print("End Date (yyyy-mm-dd): ");
                String e = sc.nextLine();

                System.out.print("Days: ");
                int d;
                try { d = Integer.parseInt(sc.nextLine()); } catch (Exception ex) { System.out.println("❌ Invalid days"); continue; }

                System.out.print("Reason: ");
                String r = sc.nextLine();

                ls.applyLeave(userId, type, s, e, r, d);
            }
            else if (c == 2) {
                ls.viewMyLeaves(userId);
            }
            else if (c == 3) {
                us.showLeaveBalance(userId);
            }
            else if (c == 4) {
                System.out.println("👋 Logged Out!");
                break;
            }
        }
    }
}
