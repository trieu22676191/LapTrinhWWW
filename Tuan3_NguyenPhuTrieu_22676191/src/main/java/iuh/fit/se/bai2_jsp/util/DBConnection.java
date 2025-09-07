package iuh.fit.se.bai2_jsp.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/usersdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "sapassword";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver MariaDB", e);
        }
    }
}
