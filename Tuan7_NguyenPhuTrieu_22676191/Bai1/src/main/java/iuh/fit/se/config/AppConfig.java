package iuh.fit.se.config;

import javax.sql.DataSource;

import iuh.fit.se.dao.EmployeeDAO;
import iuh.fit.se.dao.impl.EmployeeDAOImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:database.properties")
public class AppConfig {

    @Value("${app.datasource.url}")
    private String url;

    @Value("${app.datasource.username}")
    private String username;

    @Value("${app.datasource.password}")
    private String password;

    @Value("${app.datasource.driver}")
    private String driver;

    @Bean(name = {"DataSource", "dataSource"})
    public DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        dao.setDataSource(getDataSource()); // Inject DataSource
        return dao;
    }
}