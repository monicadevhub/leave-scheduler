package leavesystem.cli;

import java.util.Scanner;
import leavesystem.service.UserService;

public class MainMenu {

    private UserService us = new UserService();
    private Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {
            System.out.println("\n=== LEAVE SCHEDULER ===");
            System.out.println("1. Register New Employee");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int choice;
            try { choice = Integer.parseInt(sc.nextLine()); }
            catch (Exception ex) { System.out.println("❌ Invalid input"); continue; }

            if (choice == 3) { System.out.println("👋 thank you!"); break; }

            if (choice == 1) {
                System.out.print("Enter new username: ");
                String u = sc.nextLine();
                System.out.print("Enter password: ");
                String p = sc.nextLine();
                us.register(u, p);
                continue;
            }

            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (!us.login(username, password)) {
                System.out.println("❌ Invalid Login!");
                continue;
            }

            System.out.println("✅ Login Successful!");
            String role = us.getRole(username);
            int userId = us.getUserId(username);

            if ("employee".equals(role)) {
                new EmployeeMenu().menu(userId);
            } else {
                new AdminMenu().menu();
            }
        }
    }
}
