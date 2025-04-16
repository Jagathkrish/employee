package com.employee.employee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="department_info")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "did")
public class Department {

    @Id
    private String did;


    private String dname;
    private String creationdate;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonBackReference(value = "emp-dept")
    private List<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_head_id", referencedColumnName = "id")  // Foreign key for department head
    private Employee departmentHead;

    public Department() {
    }

    public Department(String did, String dname, String creationdate, List<Employee> employees, Employee departmentHead) {
        this.did = did;
        this.dname = dname;
        this.creationdate = creationdate;
        this.employees = employees;
        this.departmentHead = departmentHead;
    }

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Employee departmentHead) {
        this.departmentHead = departmentHead;
    }
}
