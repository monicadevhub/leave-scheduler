package leavesystem.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private int ctBalance;

    public User() {}

    public User(int id, String username, String role, int ctBalance) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.ctBalance = ctBalance;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getCtBalance() { return ctBalance; }
    public void setCtBalance(int ctBalance) { this.ctBalance = ctBalance; }
}
