package com.employee.employee.service.impl;

import com.employee.employee.model.Employee;
import com.employee.employee.model.Department;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.repository.DepartmentRepository;
import com.employee.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String createEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getDid() != null) {
            Department department = departmentRepository.findById(employee.getDepartment().getDid())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(department);
        }

        // Fetch reporting manager if only ID is provided
        if (employee.getReportingManager() != null && employee.getReportingManager().getId() != null) {
            Employee reportingManager = employeeRepository.findById(employee.getReportingManager().getId())
                    .orElse(null); // You could throw an exception or handle null case here
            employee.setReportingManager(reportingManager);
        }

        // Save employee to the repository
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Success";
    }

    @Override
    public String deleteEmployee(String id) {
        employeeRepository.deleteById(id);
        return "Success";
    }

    @Override
    public Employee getEmployee(String id) {
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
