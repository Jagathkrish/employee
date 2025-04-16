package com.employee.employee.controller;


import com.employee.employee.dto.EmployeeDTO;
import com.employee.employee.model.Department;
import com.employee.employee.model.Employee;
import com.employee.employee.repository.DepartmentRepository;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Default constructor
    public EmployeeController() {}

    // Constructor with parameters for dependency injection (optional)
    public EmployeeController(EmployeeService employeeService,
                              DepartmentRepository departmentRepository,
                              EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }
    //read specific employee from db
    @GetMapping("/{id}")
    public Employee getEmployeeDetails(@PathVariable("id") String id){

        // Fetch employee by ID
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;  // Or handle this case as per your requirement (e.g., throw exception)
        }

        // Return the employee object
        return employee;
    }
    //read all employees from db
    @GetMapping()
    public List<Employee> getAllEmployeeDetails(){

        return employeeService.getAllEmployee();
    }

//    @PostMapping
//    public String createEmployeeDetails(@RequestBody Employee employee){
//        employeeService.createEmployee(employee);
//        return "Employee created succesfully";
//    }
    //dto initialisation on postmapping

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            Employee reportingManager = employeeRepository.findById(employeeDTO.getReportingManagerId())
                    .orElse(null);

            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setDob(employeeDTO.getDob());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDepartment(department);
            employee.setReportingManager(reportingManager);
            employeeRepository.save(employee);

            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @PutMapping
    public String updateEmployeeDetails(@RequestBody EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setDob(dto.getDob());
        employee.setSalary(dto.getSalary());
        employee.setAddress(dto.getAddress());
        employee.setRole(dto.getRole());
        employee.setJoiningdate(dto.getJoiningdate());
        employee.setYearly_bonuspercentage(dto.getYearly_bonuspercentage());

        // Set department
        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId()).orElse(null);
            employee.setDepartment(dept);
        }

        // Set reporting manager
        if (dto.getReportingManagerId() != null) {
            Employee manager = employeeRepository.findById(dto.getReportingManagerId()).orElse(null);
            employee.setReportingManager(manager);
        }

        employeeRepository.save(employee);
        return "Employee updated successfully";
    }
    @DeleteMapping("/{id}")
    public String deleteEmployeeDetails(@PathVariable("id") String id){
        employeeService.deleteEmployee(id);
        return "Employee deleted succesfully";
    }
}
