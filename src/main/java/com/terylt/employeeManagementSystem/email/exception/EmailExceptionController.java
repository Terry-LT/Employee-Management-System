package com.terylt.employeeManagementSystem.email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailExceptionController {
    @ExceptionHandler(value = EmailNotValidException.class)
    public ResponseEntity<Object> emailNotValidException(EmailNotValidException exception){
        return new ResponseEntity<>("Email is not valid!", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
