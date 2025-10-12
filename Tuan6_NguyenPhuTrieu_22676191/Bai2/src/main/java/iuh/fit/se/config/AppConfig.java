package iuh.fit.se.config;

import iuh.fit.se.model.Address;
import iuh.fit.se.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Address address() {
        return new Address("Ha Noi", "North", "Vietnam");
    }

    @Bean
    public Employee employee() {
        return new Employee(1, "Nguyen Van A", address());
    }
}
