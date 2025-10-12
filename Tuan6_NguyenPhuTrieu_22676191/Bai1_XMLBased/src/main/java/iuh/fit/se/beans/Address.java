package iuh.fit.se.beans;

public class Address {
    private String city;
    private String country;

    public void setCity(String city) { this.city = city; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
