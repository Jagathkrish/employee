package com.employee.employee.service;

import com.employee.employee.model.Department;

import java.util.List;

public interface DepartmentService {
    public String createDepartment(Department employee);
    public String updateDepartment(Department employee);
    public String deleteDepartment(String id);
    public Department getDepartment(String id);
    public List<Department> getAllDepartment();
}
