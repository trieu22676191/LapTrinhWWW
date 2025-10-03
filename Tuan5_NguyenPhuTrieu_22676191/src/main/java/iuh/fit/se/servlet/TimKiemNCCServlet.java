package iuh.fit.se.servlet;

import iuh.fit.se.dao.NhaCungCapDAO;
import iuh.fit.se.model.NhaCungCap;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/TimKiemNCCServlet")
public class TimKiemNCCServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        List<NhaCungCap> list = (q == null || q.isEmpty()) ? NhaCungCapDAO.getAll() : NhaCungCapDAO.search(q);
        req.setAttribute("listNCC", list);
        req.getRequestDispatcher("/DanhSachNCC.jsp").forward(req, resp);
    }
}
