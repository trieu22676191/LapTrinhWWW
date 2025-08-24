package iuh.fit.se.tuan1_nguyenphutrieu_22676191;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Lấy giá trị context-param
        String websiteName = getServletContext().getInitParameter("websiteName");

        out.println("<h2>Website: " + websiteName + "</h2>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("<h2>Xin chào " + name + "</h2>");
    }

    public void destroy() {
    }
}