package com.employee.employee.dto;

import java.util.List;

public class DepartmentDTO {

    private String did;
    private String dname;
    private String creationdate;
    private String departmentHeadId;// Only ID to avoid recursion
    private List<EmployeeDTO> employees;

    // Constructors
    public DepartmentDTO() {}

    public DepartmentDTO(String did, String dname, String creationdate, String departmentHeadId, List<EmployeeDTO> employees) {
        this.did = did;
        this.dname = dname;
        this.creationdate = creationdate;
        this.departmentHeadId = departmentHeadId;
        this.employees = employees;
    }

    // Getters and Setters
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }

    public String getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(String departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}