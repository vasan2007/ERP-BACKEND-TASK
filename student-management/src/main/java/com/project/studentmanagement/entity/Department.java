package com.project.studentmanagement.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
@Entity
@Table(name="departments")
public class Department{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable=false)
    @NotBlank(message="Department code cannot be empty")
    private String departmentCode;
    @Column(nullable=false)
    @NotBlank(message="Department name cannot be empty")
    private String departmentName;
    @OneToMany(mappedBy="department")
    @JsonIgnore
    private List<Student> students;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getDepartmentCode(){
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode){
        this.departmentCode=departmentCode;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName=departmentName;
    }
    public List<Student> getStudents(){
        return students;
    }
    public void setStudents(List<Student> students){
        this.students=students;
    }
}