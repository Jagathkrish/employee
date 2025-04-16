package com.employee.employee.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name ="employee_info")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {

    @Id
    private String id;

    private String name;
    private String dob;
    private String salary;



    // Many-to-One relationship with Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")// Foreign key for department
    private Department department;


    private String address;
    private String role;
    private String joiningdate;
    private String yearly_bonuspercentage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_manager_id") // Foreign key for department
    private Employee reportingManager;// Foreign key for reporting manager

    public Employee() {
    }

    public Employee(String id, String name, String dob, String salary, Department department, String address, String role, String joiningdate, String yearly_bonuspercentage, Employee reportingManager) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.department = department;
        this.address = address;
        this.role = role;
        this.joiningdate = joiningdate;
        this.yearly_bonuspercentage = yearly_bonuspercentage;
        this.reportingManager = reportingManager;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Employee getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Employee reportingManager) {
        this.reportingManager = reportingManager;
    }
}
