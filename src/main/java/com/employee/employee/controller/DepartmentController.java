//package com.employee.employee.controller;
//
//
//import com.employee.employee.model.Department;
//import com.employee.employee.service.DepartmentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/department")
//public class DepartmentController {
//    DepartmentService departmentService;
//
//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    //read specific employee from db
//    @GetMapping("{did}")
//    public Department getDepartmentDetails(@PathVariable("did") String did){
//
//        return departmentService.getDepartment(did);
//    }
//    //read all employees from db
//    @GetMapping()
//    public List<Department> getAllDepartmentDetails(){
//
//        return departmentService.getAllDepartment();
//    }
//
//    @PostMapping
//    public String createDepartmentDetails(@RequestBody Department department){
//        departmentService.createDepartment(department);
//        return "Department created succesfully";
//    }
//
//    @PutMapping
//    public String updateEmployeeDetails(@RequestBody Department department){
//        departmentService.updateDepartment(department);
//        return "Department updated succesfully";
//    }
//
//
//    @DeleteMapping("{did}")
//    public String deleteEmployeeDetails(String did){
//        departmentService.deleteDepartment(did);
//        return "Department deleted succesfully";
//    }
//
//
//}


package com.employee.employee.controller;

import com.employee.employee.dto.DepartmentDTO;
import com.employee.employee.dto.EmployeeDTO;
import com.employee.employee.model.Department;
import com.employee.employee.model.Employee;
import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private final DepartmentService departmentService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeRepository employeeRepository) {
        this.departmentService = departmentService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{did}")
    public ResponseEntity<Department> getDepartmentDetails(@PathVariable("did") String did) {
        Department department = departmentService.getDepartment(did);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartmentDetails() {
        List<Department> departments = departmentService.getAllDepartment();
        return departments.stream().map(dept -> {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setDid(dept.getDid());
            dto.setDname(dept.getDname());
            dto.setCreationdate(dept.getCreationdate());
            if (dept.getDepartmentHead() != null) {
                dto.setDepartmentHeadId(dept.getDepartmentHead().getId());
            }
            return dto;
        }).toList();
    }

    @PostMapping
    public ResponseEntity<String> createDepartmentDetails(@RequestBody DepartmentDTO dto) {
        try {
            Department department = new Department();
            department.setDid(dto.getDid());
            department.setDname(dto.getDname());
            department.setCreationdate(dto.getCreationdate());

            if (dto.getDepartmentHeadId() != null) {
                Employee head = employeeRepository.findById(dto.getDepartmentHeadId()).orElse(null);
                if (head != null) {
                    department.setDepartmentHead(head);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department head not found");
                }
            }

            departmentService.createDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).body("Department created successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating department: " + e.getMessage());
        }
    }

    @PutMapping
    public String updateDepartmentDetails(@RequestBody DepartmentDTO dto) {
        Department department = new Department();
        department.setDid(dto.getDid());
        department.setDname(dto.getDname());
        department.setCreationdate(dto.getCreationdate());

        if (dto.getDepartmentHeadId() != null) {
            Employee departmentHead = employeeRepository.findById(dto.getDepartmentHeadId()).orElse(null);
            department.setDepartmentHead(departmentHead);
        }

        departmentService.updateDepartment(department);
        return "Department updated successfully";
    }

    @DeleteMapping("/{did}")
    public String deleteDepartmentDetails(@PathVariable("did") String did) {
        departmentService.deleteDepartment(did);
        return "Department deleted successfully";
    }

    @GetMapping("/api/{did}")
    public DepartmentDTO getDepartmentWithEmployees(@PathVariable("did") String did) {
        Department dept = departmentService.getDepartment(did);
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDid(dept.getDid());
        dto.setDname(dept.getDname());

        List<EmployeeDTO> empDTOs = dept.getEmployees().stream().map(emp -> new EmployeeDTO(emp.getId(), emp.getName())).collect(Collectors.toList());

        dto.setEmployees(empDTOs);
        return dto;
    }
}
