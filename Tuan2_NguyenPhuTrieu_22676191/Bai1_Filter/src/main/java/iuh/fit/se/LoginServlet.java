package iuh.fit.se;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", urlPatterns ={"/login"})

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, @org.jetbrains.annotations.NotNull HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write = resp.getWriter();

        write.println("Login thanh cong !");

        write.close();
    }
}
