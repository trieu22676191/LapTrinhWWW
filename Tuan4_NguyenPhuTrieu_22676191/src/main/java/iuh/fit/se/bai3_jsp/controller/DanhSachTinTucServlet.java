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

@WebServlet("/DanhSachTinTucServlet")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String sMaDM = request.getParameter("madm");
            int maDM = 0;
            if (sMaDM != null && !sMaDM.isEmpty()) maDM = Integer.parseInt(sMaDM);

            List<TinTuc> listTin = dao.getTinTucByDanhMuc(maDM);
            List<DanhMuc> dsDanhMuc = dao.getAllDanhMuc();

            request.setAttribute("listTin", listTin);
            request.setAttribute("dsDanhMuc", dsDanhMuc);
            request.setAttribute("selectedDM", maDM);

            RequestDispatcher rd = request.getRequestDispatcher("DanhSachTinTuc.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
