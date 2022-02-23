package net.javaguides.pringboot.service;

import net.javaguides.pringboot.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);
}
