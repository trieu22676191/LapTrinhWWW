package iuh.fit.se.dao;

import iuh.fit.se.model.NhaCungCap;
import iuh.fit.se.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    public static List<NhaCungCap> search(String q) {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM NHACUNGCAP WHERE MANCC LIKE ? OR TENNHACC LIKE ? OR DIACHI LIKE ? OR SODIENTHOAI LIKE ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + q + "%";
            for (int i=1;i<=4;i++) ps.setString(i, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new NhaCungCap(rs.getString("MANCC"), rs.getString("TENNHACC"), rs.getString("DIACHI"), rs.getString("SODIENTHOAI")));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        try (Connection c = DBConnect.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM NHACUNGCAP");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new NhaCungCap(rs.getString("MANCC"), rs.getString("TENNHACC"), rs.getString("DIACHI"), rs.getString("SODIENTHOAI")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
