package iuh.fit.se.bai2_jsp.dao;
import iuh.fit.se.bai2_jsp.model.User;
import iuh.fit.se.bai2_jsp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users(first_name, last_name, email, password, gender, birthday) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getBirthday());

            ps.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT first_name, last_name, email, gender, birthday FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                User u = new User();
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setGender(rs.getString("gender"));
                u.setBirthday(rs.getString("birthday"));
                list.add(u);
            }
        }
        return list;
    }
}
