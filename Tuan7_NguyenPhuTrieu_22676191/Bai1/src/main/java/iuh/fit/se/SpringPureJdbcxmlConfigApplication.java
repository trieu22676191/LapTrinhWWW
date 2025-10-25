package iuh.fit.se;

// Đảm bảo bạn đã import đúng các class
import iuh.fit.se.config.AppConfig;
import iuh.fit.se.dao.EmployeeDAO;
import iuh.fit.se.entities.Employee;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// Import này dùng cho Bài 1, có thể giữ lại hoặc xóa
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringPureJdbcxmlConfigApplication {

    @SuppressWarnings({"resource", "deprecation"})
    public static void main(String[] args) throws SQLException {

        // KHỞI TẠO CONTEXT CHO BÀI 2 (ANNOTATION)
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DataSource dataSource = context.getBean("DataSource", DataSource.class);
        System.out.println("Kết nối Database thành công: " + dataSource.getConnection());

        // PHẦN KHỞI TẠO CONTEXT CỦA BÀI 1 (XML) ===
        // ApplicationContext context = new ClassPathXmlApplicationContext("databaseConfig.xml");


        EmployeeDAO employeeDAO = (EmployeeDAO) context.getBean("employeeDAO");

        System.out.println("--- GHI THÊM 2 NHÂN VIÊN ---");
        //employeeDAO.save(new Employee(1001, "Manager", "Alice"));
        //employeeDAO.save(new Employee(1002, "Developer", "Bob"));
        employeeDAO.save(new Employee(1003, "Manager", "Teerius"));
        employeeDAO.save(new Employee(1004, "Developer", "FusChiu"));

        System.out.println("\n--- LẤY TẤT CẢ NHÂN VIÊN ---");
        List<Employee> allEmployees = employeeDAO.getAll();
        for (Employee emp : allEmployees) {
            System.out.println(emp);
        }

        System.out.println("\n--- TÌM NHÂN VIÊN ID 1001 ---");
        Employee emp1001 = employeeDAO.getById(1001);
        System.out.println("Tìm thấy: " + emp1001);

        System.out.println("\n--- CẬP NHẬT NHÂN VIÊN ID 1002 ---");
        Employee emp1002 = employeeDAO.getById(1002);
        emp1002.setRole("Senior Developer");
        employeeDAO.update(emp1002);
        System.out.println("Sau cập nhật: " + employeeDAO.getById(1002));

        System.out.println("\n--- XÓA NHÂN VIÊN ID 1001 ---");
        employeeDAO.deleteById(1001);

        System.out.println("\n--- LẤY TẤT CẢ SAU KHI XÓA ---");
        List<Employee> remainingEmployees = employeeDAO.getAll();
        for (Employee emp : remainingEmployees) {
            System.out.println(emp);
        }
    }
}