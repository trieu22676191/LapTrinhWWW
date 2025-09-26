package iuh.fit.se.bai1_jsp.controller;

import iuh.fit.se.bai1_jsp.dao.UserDAO;
import iuh.fit.se.bai1_jsp.dao.impl.UserImpl;
import iuh.fit.se.bai1_jsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(urlPatterns = "/userList", loadOnStartup = 1)
public class UserList extends HttpServlet {
    private UserDAO userDao = new UserImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userDao.findAllUsers();
            req.setAttribute("userList", users);
            req.getRequestDispatcher("/userList.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
