package iuh.fit.se.bai2_jsp.controller;

import iuh.fit.se.bai2_jsp.dao.UserDAO;
import iuh.fit.se.bai2_jsp.model.User;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserRegistrationServlet", value = "/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("day") + "/" +
                req.getParameter("month") + "/" +
                req.getParameter("year");

        User user = new User(firstName, lastName, email, password, gender, birthday);

        try {
            userDAO.insertUser(user);
            List<User> users = userDAO.getAllUsers();
            req.setAttribute("userList", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("userList.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi thêm user", e);
        }
    }
}
