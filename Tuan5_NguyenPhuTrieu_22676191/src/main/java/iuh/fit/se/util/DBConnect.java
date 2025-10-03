package iuh.fit.se.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static final String URL = "jdbc:mariadb://localhost:3306/QUANLYDIENTHOAI";
    private static final String USER = "root";         // đổi nếu cần
    private static final String PASS = "sapassword";   // đổi theo máy bạn

    public static Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
