package iuh.fit.se.tuan1_nguyenphutrieu_22676191;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // không in ra
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobby");
        String country = request.getParameter("country");
        String about = request.getParameter("about");

        PrintWriter out = response.getWriter();
        out.println("<h2>Thông tin bạn vừa nhập:</h2>");
        out.println("Họ và tên: " + fullname + "<br>");
        out.println("Email: " + email + "<br>");
        out.println("Giới tính: " + gender + "<br>");

        if (hobbies != null) {
            out.print("Sở thích: ");
            for (String h : hobbies) {
                out.print(h + " ");
            }
            out.println("<br>");
        }

        out.println("Quốc gia: " + country + "<br>");
        out.println("Giới thiệu: " + about + "<br>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp); // để test GET dễ dàng
    }
}
