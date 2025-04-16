package com.employee.employee.dto;

public class EmployeeDTO {
    private String id;
    private String name;
    private String dob;
    private String salary;
    private String departmentId;
    private String address;
    private String role;
    private String joiningdate;
    private String yearly_bonuspercentage;
    private String reportingManagerId;


    public EmployeeDTO() {
    }
    // Constructor with parameters
    public EmployeeDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeDTO(String id, String name, String dob, String salary, String departmentId, String address, String role, String joiningdate, String yearly_bonuspercentage, String reportingManagerId) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.departmentId = departmentId;
        this.address = address;
        this.role = role;
        this.joiningdate = joiningdate;
        this.yearly_bonuspercentage = yearly_bonuspercentage;
        this.reportingManagerId = reportingManagerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public String getYearly_bonuspercentage() {
        return yearly_bonuspercentage;
    }

    public void setYearly_bonuspercentage(String yearly_bonuspercentage) {
        this.yearly_bonuspercentage = yearly_bonuspercentage;
    }

    public String getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(String reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }


    // Getters & Setters
    // You can use Lombok (@Getter, @Setter) if you want to reduce boilerplate
}