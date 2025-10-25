package iuh.fit.se.entities;

public class Employee {
    private int id;
    private String role;
    private String name;

    public Employee() {
    }

    public Employee(int id, String role, String name) {
        this.id = id;
        this.role = role;
        this.name = name;
    }

    // 3. Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", role=" + role + ", name=" + name + "]";
    }
}