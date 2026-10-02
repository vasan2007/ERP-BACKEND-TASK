package com.project.studentmanagement.service;
import com.project.studentmanagement.entity.Department;
import com.project.studentmanagement.repository.DepartmentRepository;
import com.project.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StatisticsService{
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    public StatisticsService(StudentRepository studentRepository,DepartmentRepository departmentRepository){
        this.studentRepository=studentRepository;
        this.departmentRepository=departmentRepository;
    }
    public Map<String,Object> getStatistics(){
        Map<String,Object> response=new LinkedHashMap<>();
        response.put("totalStudents",studentRepository.count());
        Map<String,Long> departmentWise=new LinkedHashMap<>();
        for(Department department:departmentRepository.findAll()){
            departmentWise.put(department.getDepartmentCode(),studentRepository.countByDepartment_DepartmentCode(department.getDepartmentCode()));
        }
        Map<String,Long> yearWise=new LinkedHashMap<>();
        for(int i=1;i<=4;i++){
            yearWise.put(String.valueOf(i),studentRepository.countByYear(i));
        }
        response.put("departmentWise",departmentWise);
        response.put("yearWise",yearWise);
        return response;
    }
}