package iuh.fit.se.beans;

public class User {
    private int id;
    private String userName;
    private String password;
    private Group group;

    public User() {}

    public User(int id, String userName, String password, Group group) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.group = group;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    @Override
    public String toString() {
        return "User{id=" + id + ", userName='" + userName + "', password='" + password +
                "', group=" + group + "}";
    }
}
