package iuh.fit.se.dao.impl;

import iuh.fit.se.dao.EmployeeDAO;
import iuh.fit.se.entities.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // Thuộc tính DataSource theo sơ đồ lớp
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    // Setter cho DataSource (Spring sẽ gọi phương thức này để inject bean 'dataSource')
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // RowMapper để ánh xạ kết quả từ ResultSet sang đối tượng Employee
    private static final class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setRole(rs.getString("role"));
            return employee;
        }
    }

    // ----------------- CÁC PHƯƠNG THỨC CRUD -----------------

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employee (id, name, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getId(), employee.getName(), employee.getRole());
        System.out.println("Created Record ID = " + employee.getId());
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        // Lấy 1 đối tượng duy nhất
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeMapper());
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        // Lấy danh sách đối tượng
        return jdbcTemplate.query(sql, new EmployeeMapper());
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET name = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getRole(), employee.getId());
        System.out.println("Updated Record ID = " + employee.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Deleted Record ID = " + id);
    }
}