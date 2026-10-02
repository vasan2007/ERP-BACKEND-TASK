package com.project.studentmanagement.controller;
import com.project.studentmanagement.entity.Student;
import com.project.studentmanagement.service.StudentService;
import com.project.studentmanagement.service.StatisticsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/students")
public class StudentController{
    private final StudentService studentService;
    private final StatisticsService statisticsService;
    public StudentController(StudentService studentService,StatisticsService statisticsService){
        this.studentService=studentService;
        this.statisticsService=statisticsService;
    }
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required=false) String department,@RequestParam(required=false) Integer year,@RequestParam(required=false) Integer semester){
        return ResponseEntity.ok(studentService.filter(department,year,semester));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(student));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@Valid @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(id,student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(Map.of("message","Student deleted successfully"));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudent(@RequestParam(required=false) String name,@RequestParam(required=false) String registerNo){
        if(name!=null){
            return ResponseEntity.ok(studentService.searchByName(name));
        }
        if(registerNo!=null){
            return ResponseEntity.ok(studentService.searchByRegisterNo(registerNo));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/statistics")
    public ResponseEntity<Map<String,Object>> statistics(){
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}