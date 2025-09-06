package iuh.fit.se.uploadfiletodatabase;

import iuh.fit.se.uploadfiletodatabase.databaseconnector.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "fileUploadToDatabaseServlet", value = "/uploadToDataBase")
@MultipartConfig
public class FileUpLoadToDatabaseServlet extends HttpServlet {
    private String uploadPathToSource;

    @Override
    public void init(jakarta.servlet.ServletConfig config) throws ServletException {
        super.init(config);
        String userDir = System.getProperty("user.dir");
        if (userDir.contains("tomcat") && userDir.endsWith("bin")) {
            userDir = new File(userDir).getParent();
            userDir = new File(userDir).getParent();
        }
        uploadPathToSource = "D:\\Java_WWW\\BTTH\\Tuan2_NguyenPhuTrieu_22676191\\Bai3_UploadAndSave\\src\\main\\webapp\\uploads";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        InputStream inputStream = null;
        String message = null;

        // Đặt kiểu nội dung phản hồi là HTML
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            Part filePart = req.getPart("photo");

            if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
                String fileUploadName = filePart.getSubmittedFileName();
                inputStream = filePart.getInputStream();

                // Lưu file vào hệ thống tệp
                File file = new File(uploadPathToSource, fileUploadName);
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            // Mở lại luồng dữ liệu để lưu vào CSDL
            if (filePart != null) {
                inputStream = filePart.getInputStream();
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sqlInsert = "INSERT INTO contacts (first_name, last_name, photo) values (?, ?, ?)";
                System.out.println(firstName + lastName + inputStream);
                try (PreparedStatement statement = conn.prepareStatement(sqlInsert)) {
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    if (inputStream != null) {
                        statement.setBlob(3, inputStream);
                    }
                    int row = statement.executeUpdate();
                    if (row > 0) {
                        message = "File uploaded and saved into database";
                    }
                }
            }
        } catch (SQLException e) {
            message = "ERROR: " + e.getMessage();
            e.printStackTrace();
        }

        out.println("<html>");
        out.println("<head><title>Upload Status</title></head>");
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}