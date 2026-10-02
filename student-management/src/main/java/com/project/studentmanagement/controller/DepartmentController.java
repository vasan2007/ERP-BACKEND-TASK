package com.project.studentmanagement.controller;
import com.project.studentmanagement.entity.Department;
import com.project.studentmanagement.entity.Student;
import com.project.studentmanagement.service.DepartmentService;
import com.project.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/departments")
public class DepartmentController{
    private final DepartmentService departmentService;
    private final StudentService studentService;
    public DepartmentController(DepartmentService departmentService,StudentService studentService){
        this.departmentService=departmentService;
        this.studentService=studentService;
    }
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }
    @PostMapping
    public ResponseEntity<Department> addDepartment(@Valid @RequestBody Department department){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(department));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,@Valid @RequestBody Department department){
        return ResponseEntity.ok(departmentService.updateDepartment(id,department));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }
    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getDepartmentStudents(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentsByDepartment(id));
    }
}
