package iuh.fit.se.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String usernameConfig = this.getFilterConfig().getInitParameter("username");
        String passwordConfig = this.getFilterConfig().getInitParameter("password");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(usernameConfig) && password.equals(passwordConfig)) {
            chain.doFilter(req, res);
        } else  {
            PrintWriter writer = res.getWriter();

            writer.println("Thong tin khong dung !");
            writer.close();
        }


    }
}
