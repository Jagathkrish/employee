package com.employee.employee.service;

import com.employee.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    public String createEmployee(Employee employee);
    public String updateEmployee(Employee employee);
    public String deleteEmployee(String id);
    public Employee getEmployee(String id);
    public List<Employee> getAllEmployee();

}
