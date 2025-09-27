package iuh.fit.se.bai3_jsp.controller;

import iuh.fit.se.bai3_jsp.dao.DanhSachTinTucQuanLy;
import iuh.fit.se.bai3_jsp.model.TinTuc;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao = new DanhSachTinTucQuanLy();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("delete".equals(action)) {
                String sId = request.getParameter("id");
                if (sId != null) {
                    dao.xoaTinTuc(Integer.parseInt(sId));
                }
                response.sendRedirect("QuanLyFormServlet");
                return;
            }

            List<TinTuc> listTin = dao.getTinTucByDanhMuc(0);
            request.setAttribute("listTin", listTin);
            RequestDispatcher rd = request.getRequestDispatcher("QuanLyForm.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

