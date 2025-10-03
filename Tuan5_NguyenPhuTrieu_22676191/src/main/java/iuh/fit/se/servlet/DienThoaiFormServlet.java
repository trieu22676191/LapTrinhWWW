package iuh.fit.se.servlet;

import iuh.fit.se.dao.DienThoaiDAO;
import iuh.fit.se.model.DienThoai;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.nio.file.*;

@WebServlet("/DienThoaiFormServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024, maxRequestSize = 10*1024*1024)
public class DienThoaiFormServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "D:\\Java_WWW\\BTTH\\Tuan5_NguyenPhuTrieu_22676191\\src\\main\\webapp\\images";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String maDT = req.getParameter("maDT");
        String tenDT = req.getParameter("tenDT");
        String namStr = req.getParameter("namSX");
        String cauHinh = req.getParameter("cauHinh");
        String maNCC = req.getParameter("maNCC");

        String error = null;
        if (maDT == null || maDT.isBlank() || tenDT == null || tenDT.isBlank() ||
                namStr == null || namStr.isBlank() || cauHinh == null || cauHinh.isBlank()) {
            error = "Các trường Mã, Tên, Năm, Cấu hình là bắt buộc!";
        }
        if (error == null && !namStr.matches("\\d{4}")) {
            error = "Năm sản xuất phải là 4 chữ số!";
        }
        if (error == null && cauHinh.length() > 255) {
            error = "Cấu hình không quá 255 ký tự!";
        }

        Part filePart = req.getPart("hinhAnh");
        String filename = null;
        if (filePart != null && filePart.getSize() > 0) {
            String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            if (!submitted.toLowerCase().matches(".*\\.(png|jpg|jpeg)$")) {
                error = "Chỉ chấp nhận ảnh png, jpg, jpeg";
            } else {
                filename = System.currentTimeMillis() + "_" + submitted;
                File imagesDir = new File(UPLOAD_DIR);
                if (!imagesDir.exists()) imagesDir.mkdirs();
                File file = new File(imagesDir, filename);
                try (InputStream in = filePart.getInputStream()) {
                    Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                    error = "Lỗi lưu ảnh lên server";
                }
            }
        } else {
            error = "Bạn phải chọn file ảnh!";
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/DienThoaiForm.jsp").forward(req, resp);
            return;
        }

        int namSX = Integer.parseInt(namStr);
        DienThoai dt = new DienThoai(maDT, tenDT, namSX, cauHinh, filename, maNCC);
        boolean ok = DienThoaiDAO.insert(dt);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/DanhSachDienThoaiNCCServlet");
        } else {
            req.setAttribute("error", "Thêm thất bại (kiểm tra mã trùng)");
            req.getRequestDispatcher("/DienThoaiForm.jsp").forward(req, resp);
        }
    }
}

