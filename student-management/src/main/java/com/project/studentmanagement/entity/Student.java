package com.project.studentmanagement.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
@Entity
@Table(name="students")
public class Student{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="register_no",unique=true,nullable=false)
    @NotBlank(message="Register number cannot be empty")
    private String registerNo;
    @Column(nullable=false)
    @NotBlank(message="Name cannot be empty")
    private String name;
    @Column(nullable=false)
    @NotBlank(message="Email cannot be empty")
    @Email(message="Invalid email address")
    private String email;
    @Column(nullable=false)
    @NotBlank(message="Phone number cannot be empty")
    @Pattern(regexp="^[0-9]{10}$",message="Invalid phone number")
    private String phone;
    @ManyToOne
    @JoinColumn(name="department_id",nullable=false)
    @NotNull(message="Department cannot be empty")
    private Department department;
    @Column(nullable=false)
    @NotNull(message="Year cannot be empty")
    @Min(value=1,message="Year must be between 1 and 4")
    @Max(value=4,message="Year must be between 1 and 4")
    private Integer year;
    @Column(nullable=false)
    @NotNull(message="Semester cannot be empty")
    @Min(value=1,message="Semester must be between 1 and 8")
    @Max(value=8,message="Semester must be between 1 and 8")
    private Integer semester;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    public void beforeCreate(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }
    @PreUpdate
    public void beforeUpdate(){
        updatedAt=LocalDateTime.now();
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getRegisterNo(){
        return registerNo;
    }
    public void setRegisterNo(String registerNo){
        this.registerNo=registerNo;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public Department getDepartment(){
        return department;
    }
    public void setDepartment(Department department){
        this.department=department;
    }
    public Integer getYear(){
        return year;
    }
    public void setYear(Integer year){
        this.year=year;
    }
    public Integer getSemester(){
        return semester;
    }
    public void setSemester(Integer semester){
        this.semester=semester;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt=updatedAt;
    }
}