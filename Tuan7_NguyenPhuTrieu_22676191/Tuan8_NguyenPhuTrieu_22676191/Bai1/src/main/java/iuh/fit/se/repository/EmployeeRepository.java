package iuh.fit.se.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import iuh.fit.se.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
