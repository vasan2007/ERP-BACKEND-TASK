package com.project.studentmanagement.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String,Object>> studentNotFound(StudentNotFoundException e){
        Map<String,Object> response=new HashMap<>();
        response.put("status",404);
        response.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Map<String,Object>> departmentNotFound(DepartmentNotFoundException e){
        Map<String,Object> response=new HashMap<>();
        response.put("status",404);
        response.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(DuplicateRegisterException.class)
    public ResponseEntity<Map<String,Object>> duplicateRegister(DuplicateRegisterException e){
        Map<String,Object> response=new HashMap<>();
        response.put("status",409);
        response.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validationError(MethodArgumentNotValidException e){
        Map<String,Object> response=new HashMap<>();
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        response.put("status",400);
        response.put("message","Invalid request");
        response.put("errors",errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}