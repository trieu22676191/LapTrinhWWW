package iuh.fit.se.bai3_jsp.dao;

import java.sql.*;
import java.util.*;
import iuh.fit.se.bai3_jsp.model.TinTuc;
import iuh.fit.se.bai3_jsp.model.DanhMuc;
import iuh.fit.se.bai3_jsp.until.DBConnection;

import java.sql.*;
import java.util.*;

public class DanhSachTinTucQuanLy {

    public List<DanhMuc> getAllDanhMuc() throws SQLException {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT MADM, TENDANHMUC FROM DANHMUC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(rs.getInt("MADM"), rs.getString("TENDANHMUC")));
            }
        }
        return list;
    }

    public List<TinTuc> getTinTucByDanhMuc(int maDM) throws SQLException {
        List<TinTuc> list = new ArrayList<>();
        String sql;
        if (maDM <= 0) {
            sql = "SELECT * FROM TINTUC ORDER BY MATT DESC";
        } else {
            sql = "SELECT * FROM TINTUC WHERE MADM = ? ORDER BY MATT DESC";
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (maDM > 0) ps.setInt(1, maDM);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TinTuc(
                            rs.getInt("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            rs.getInt("MADM")
                    ));
                }
            }
        }
        return list;
    }

    public void themTinTuc(TinTuc tt) throws SQLException {
        String sql = "INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tt.getTieuDe());
            ps.setString(2, tt.getNoiDungTT());
            ps.setString(3, tt.getLienKet());
            if (tt.getMaDM() > 0) ps.setInt(4, tt.getMaDM());
            else ps.setNull(4, Types.INTEGER);
            ps.executeUpdate();
        }
    }

    public void xoaTinTuc(int maTT) throws SQLException {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTT);
            ps.executeUpdate();
        }
    }
}

