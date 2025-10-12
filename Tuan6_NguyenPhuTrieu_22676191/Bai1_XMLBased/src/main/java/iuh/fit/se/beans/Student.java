package iuh.fit.se.beans;

public class Student {
    private String id;
    private String name;
    private Address address;

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', address=" + address + "}";
    }
}
