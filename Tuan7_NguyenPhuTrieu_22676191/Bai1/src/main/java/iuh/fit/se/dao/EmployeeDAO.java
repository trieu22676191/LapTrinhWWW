package iuh.fit.se.dao;

import iuh.fit.se.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    public void update(Employee employee);
    public List<Employee> getAll();
    public Employee getById(int id);
    public void deleteById(int id);
    public void save(Employee employee);
    public Employee getByIdDirectMapper(int id);
}