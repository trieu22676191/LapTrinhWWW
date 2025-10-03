package iuh.fit.se.servlet;

import iuh.fit.se.dao.DienThoaiDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String maDT = req.getParameter("maDT");
        if ("delete".equals(action) && maDT != null) {
            DienThoaiDAO.delete(maDT);
        }
        resp.sendRedirect(req.getContextPath() + "/DanhSachDienThoaiNCCServlet");
    }
}
