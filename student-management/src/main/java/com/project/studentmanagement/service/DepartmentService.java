package com.project.studentmanagement.service;
import com.project.studentmanagement.entity.Department;
import com.project.studentmanagement.exception.DepartmentNotFoundException;
import com.project.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DepartmentService{
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    public Department getDepartment(Long id){
        return departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
    }
    public Department addDepartment(Department department){
        if(departmentRepository.existsByDepartmentCode(department.getDepartmentCode())){
            throw new RuntimeException("Department code already exists");
        }
        return departmentRepository.save(department);
    }
    public Department updateDepartment(Long id,Department department){
        Department existing=getDepartment(id);
        existing.setDepartmentCode(department.getDepartmentCode());
        existing.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(existing);
    }
    public void deleteDepartment(Long id){
        Department department=getDepartment(id);
        departmentRepository.delete(department);
    }
}