package iuh.fit.se.tuan1_nguyenphutrieu_22676191;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.tuan1_nguyenphutrieu_22676191.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        User user = new User("Trieu", 21, "trieu@gmail.com");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);

        resp.getWriter().write(jsonString);
    }
}