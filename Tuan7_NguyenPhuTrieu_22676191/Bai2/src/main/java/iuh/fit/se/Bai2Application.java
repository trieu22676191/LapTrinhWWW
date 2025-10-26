package iuh.fit.se;

import iuh.fit.se.entity.Employee;
import iuh.fit.se.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Bai2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bai2Application.class, args);
    }

    @Bean
    CommandLineRunner run(EmployeeRepository repo) {
        return args -> {
            System.out.println("Hello JPA Spring Boot");


            repo.save(new Employee("Jiren", "Developer"));
            repo.save(new Employee("MeneQS", "Manager"));
            repo.save(new Employee("DanHoan", "Developer"));


            Employee e1 = repo.findById(1L).orElse(null);
            System.out.println("Employee with ID 1: " + e1);


            List<Employee> developers = repo.findByRole("Developer");
            System.out.println("Developers: ");
            developers.forEach(System.out::println);

            List<Employee> nameContainsVan = repo.findByNameContaining("Van");
            System.out.println("Employees with 'Van' in their name: ");
            nameContainsVan.forEach(System.out::println);


            if (e1 != null) {
                e1.setRole("Senior Developer");
                repo.save(e1);
                System.out.println("Updated Employee with ID 1: " + e1);
            }

            //Xoa
            repo.deleteById(2L);
            System.out.println("Deleted Employee with ID 2");
        };
    }
}
