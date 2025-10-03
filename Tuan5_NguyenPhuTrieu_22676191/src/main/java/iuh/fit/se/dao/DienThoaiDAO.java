package iuh.fit.se.dao;

import iuh.fit.se.model.DienThoai;
import iuh.fit.se.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DienThoaiDAO {
    public static List<DienThoai> getListByNCC(String maNCC) {
        List<DienThoai> list = new ArrayList<>();
        String sql = "SELECT * FROM DIENTHOAI WHERE MANCC = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNCC);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DienThoai d = new DienThoai(
                            rs.getString("MADT"),
                            rs.getString("TENDT"),
                            rs.getInt("NAMSANXUAT"),
                            rs.getString("CAUHINH"),
                            rs.getString("HINHANH"),
                            rs.getString("MANCC")
                    );
                    list.add(d);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static List<DienThoai> getAll() {
        List<DienThoai> list = new ArrayList<>();
        String sql = "SELECT * FROM DIENTHOAI";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DienThoai(
                        rs.getString("MADT"),
                        rs.getString("TENDT"),
                        rs.getInt("NAMSANXUAT"),
                        rs.getString("CAUHINH"),
                        rs.getString("HINHANH"),
                        rs.getString("MANCC")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static boolean insert(DienThoai dt) {
        String sql = "INSERT INTO DIENTHOAI(MADT,TENDT,NAMSANXUAT,CAUHINH,MANCC,HINHANH) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt.getMaDT());
            ps.setString(2, dt.getTenDT());
            ps.setInt(3, dt.getNamSX());
            ps.setString(4, dt.getCauHinh());
            ps.setString(5, dt.getMaNCC());
            ps.setString(6, dt.getHinhAnh());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(String maDT) {
        String sql = "DELETE FROM DIENTHOAI WHERE MADT=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maDT);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
