package com.terylt.employeeManagementSystem.appuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhoneNumberExceptionController {
    @ExceptionHandler(value = PhoneNumberNotValidException.class)
    public ResponseEntity<Object> phoneNumberNotValidException(PhoneNumberNotValidException exception){
        return new ResponseEntity<>("Phone number is not valid!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
