package iuh.fit.se.bai1_jsp.servlet;

import iuh.fit.se.bai1_jsp.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration-form")
public class RegistrationFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationFormServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form (name trong form trùng 100% với getParameter)
        String fname = request.getParameter("firstName");
        String lname = request.getParameter("lastName");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobileNumber");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pinCode = request.getParameter("pinCode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String[] hobbiesArr = request.getParameterValues("hobbies");
        String course = request.getParameter("course");

        // Xử lý ngày sinh
        String birthdate = day + "/" + month + "/" + year;

        // Xử lý hobbies (nối thành chuỗi)
        String hobbies = (hobbiesArr != null) ? String.join(", ", hobbiesArr) : "";

        // Set data vào Student
        Student sv = new Student();
        sv.setFirstName(fname);
        sv.setLastName(lname);
        sv.setDateOfBirth(birthdate);
        sv.setEmail(email);
        sv.setMobileNumber(mobileNumber);
        sv.setGender(gender);
        sv.setAddress(address);
        sv.setCity(city);
        sv.setPinCode(pinCode);
        sv.setState(state);
        sv.setCountry(country);
        sv.setHobbies(hobbies);
        sv.setCourseApplied(course);

        // Đẩy object sang JSP
        request.setAttribute("student", sv);

        // Forward sang result-form.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result-form.jsp");
        rd.forward(request, response);
    }
}

