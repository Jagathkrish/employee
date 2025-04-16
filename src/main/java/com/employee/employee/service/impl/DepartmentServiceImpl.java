package com.employee.employee.service.impl;

import com.employee.employee.model.Department;
import com.employee.employee.repository.DepartmentRepository;
import com.employee.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String createDepartment(Department department) {
        departmentRepository.save(department);
        return "Success";
    }

    @Override
    public String updateDepartment(Department department) {
        departmentRepository.save(department);
        return "Department Updated Succesfully";
    }

    @Override
    public String deleteDepartment(String did) {
        Department department = departmentRepository.findById(did)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Check if the department has any employees
        if (department.getEmployees() != null && !department.getEmployees().isEmpty()) {
            return "Cannot delete department. It still has employees assigned.";
        }

        // Safe to delete
        departmentRepository.deleteById(did);
        return "Department deleted successfully.";
    }

    @Override
    public Department getDepartment(String did) {
        return departmentRepository.findById(did).orElse(null);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }
}
