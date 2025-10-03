package iuh.fit.se.servlet;

import iuh.fit.se.dao.DienThoaiDAO;
import iuh.fit.se.model.DienThoai;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/DanhSachDienThoaiNCCServlet")
public class DanhSachDienThoaiNCCServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maNCC = req.getParameter("mancc");
        List<DienThoai> list;
        if (maNCC == null || maNCC.isEmpty()) {
            list = DienThoaiDAO.getAll();
        } else {
            list = DienThoaiDAO.getListByNCC(maNCC);
        }
        req.setAttribute("listDT", list);
        req.getRequestDispatcher("/DanhSachDienThoaiNCC.jsp").forward(req, resp);
    }
}
