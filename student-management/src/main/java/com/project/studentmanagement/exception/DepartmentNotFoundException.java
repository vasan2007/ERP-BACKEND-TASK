package com.project.studentmanagement.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
