package com.project.studentmanagement.repository;
import com.project.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student,Long>{
    boolean existsByRegisterNo(String registerNo);
    Optional<Student> findByRegisterNo(String registerNo);
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByRegisterNoContainingIgnoreCase(String registerNo);
    List<Student> findByDepartment_DepartmentCode(String departmentCode);
    List<Student> findByYear(Integer year);
    List<Student> findBySemester(Integer semester);
    List<Student> findByDepartment_DepartmentCodeAndYear(String departmentCode,Integer year);
    List<Student> findByDepartment_Id(Long departmentId);
    long countByDepartment_DepartmentCode(String departmentCode);
    long countByYear(Integer year);
}