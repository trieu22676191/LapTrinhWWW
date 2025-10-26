package iuh.fit.se.repository;

import iuh.fit.se.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom finder methods (Spring Data JPA sẽ tự sinh query)
    List<Employee> findByRole(String role);
    List<Employee> findByNameContaining(String keyword);
}
