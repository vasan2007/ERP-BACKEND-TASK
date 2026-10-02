package com.project.studentmanagement.service;
import com.project.studentmanagement.entity.Department;
import com.project.studentmanagement.entity.Student;
import com.project.studentmanagement.exception.DepartmentNotFoundException;
import com.project.studentmanagement.exception.DuplicateRegisterException;
import com.project.studentmanagement.exception.StudentNotFoundException;
import com.project.studentmanagement.repository.DepartmentRepository;
import com.project.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentService{
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    public StudentService(StudentRepository studentRepository,DepartmentRepository departmentRepository){
        this.studentRepository=studentRepository;
        this.departmentRepository=departmentRepository;
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student not found"));
    }
    public Student addStudent(Student student){
        if(studentRepository.existsByRegisterNo(student.getRegisterNo())){
            throw new DuplicateRegisterException("Register number already exists");
        }
        if(student.getDepartment()==null||student.getDepartment().getDepartmentCode()==null){
            throw new DepartmentNotFoundException("Department not found");
        }
        Department department=departmentRepository.findByDepartmentCode(student.getDepartment().getDepartmentCode()).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        student.setDepartment(department);
        return studentRepository.save(student);
    }
    public Student updateStudent(Long id,Student student){
        Student existing=getStudentById(id);
        Optional<Student> duplicate=studentRepository.findByRegisterNo(student.getRegisterNo());
        if(duplicate.isPresent()&&!duplicate.get().getId().equals(id)){
            throw new DuplicateRegisterException("Register number already exists");
        }
        if(student.getDepartment()==null||student.getDepartment().getDepartmentCode()==null){
            throw new DepartmentNotFoundException("Department not found");
        }
        Department department=departmentRepository.findByDepartmentCode(student.getDepartment().getDepartmentCode()).orElseThrow(()->new DepartmentNotFoundException("Department not found"));
        existing.setRegisterNo(student.getRegisterNo());
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        existing.setDepartment(department);
        existing.setYear(student.getYear());
        existing.setSemester(student.getSemester());
        return studentRepository.save(existing);
    }
    public void deleteStudent(Long id){
        Student student=getStudentById(id);
        studentRepository.delete(student);
    }
    public List<Student> searchByName(String name){
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Student> searchByRegisterNo(String registerNo){
        return studentRepository.findByRegisterNoContainingIgnoreCase(registerNo);
    }
    public List<Student> filter(String department,Integer year,Integer semester){
        if(department!=null&&!department.isEmpty()&&year!=null){
            return studentRepository.findByDepartment_DepartmentCodeAndYear(department,year);
        }
        if(department!=null&&!department.isEmpty()){
            return studentRepository.findByDepartment_DepartmentCode(department);
        }
        if(year!=null){
            return studentRepository.findByYear(year);
        }
        if(semester!=null){
            return studentRepository.findBySemester(semester);
        }
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByDepartment(Long id){
        if(!departmentRepository.existsById(id)){
            throw new DepartmentNotFoundException("Department not found");
        }
        return studentRepository.findByDepartment_Id(id);
    }
}