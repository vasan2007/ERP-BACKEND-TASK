package com.project.studentmanagement.exception;

public class DuplicateRegisterException extends RuntimeException{
    public DuplicateRegisterException(String message){
        super(message);
    }
}