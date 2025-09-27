package iuh.fit.se.bai3_jsp.controller;

import iuh.fit.se.bai3_jsp.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.bai3_jsp.model.DanhMuc;
import iuh.fit.se.bai3_jsp.model.TinTuc;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/TinTucFormServlet")
public class TinTucFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<DanhMuc> dsDanhMuc = dao.getAllDanhMuc();
            request.setAttribute("dsDanhMuc", dsDanhMuc);
            RequestDispatcher rd = request.getRequestDispatcher("TinTucForm.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String tieuDe = request.getParameter("tieude");
        String noiDung = request.getParameter("noidung");
        String lienKet = request.getParameter("lienket");
        String sMaDM = request.getParameter("madm");

        String error = null;

        // Kiểm tra bắt buộc
        if (tieuDe == null || tieuDe.trim().isEmpty()
                || noiDung == null || noiDung.trim().isEmpty()
                || lienKet == null || lienKet.trim().isEmpty()
                || sMaDM == null || sMaDM.trim().isEmpty()) {
            error = "Các trường Tiêu đề, Nội dung, Liên kết và Danh mục là bắt buộc.";
        }

        if (error == null) {
            if (!Pattern.matches("^http://.*", lienKet)) {
                error = "Liên kết phải bắt đầu bởi \"http://\"";
            }
        }

        if (error == null) {
            if (noiDung.length() > 255) {
                error = "Nội dung không được vượt quá 255 ký tự.";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
            try {
                request.setAttribute("dsDanhMuc", dao.getAllDanhMuc());
            } catch (Exception ignored) {}
            RequestDispatcher rd = request.getRequestDispatcher("TinTucForm.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            int maDM = Integer.parseInt(sMaDM);
            TinTuc tt = new TinTuc(0, tieuDe.trim(), noiDung.trim(), lienKet.trim(), maDM);
            dao.themTinTuc(tt);

            request.setAttribute("message", "Thêm tin tức thành công!");
            RequestDispatcher rd = request.getRequestDispatcher("KetQua.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
