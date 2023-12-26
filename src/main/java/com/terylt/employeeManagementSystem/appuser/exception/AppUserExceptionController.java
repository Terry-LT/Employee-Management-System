package com.terylt.employeeManagementSystem.appuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppUserExceptionController {
    @ExceptionHandler(value = AppUserNameTakenException.class)
    public ResponseEntity<Object> appUserNameTakenException(AppUserNameTakenException exception){
        return new ResponseEntity<>("User with such username already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = AppUserNotExistException.class)
    public ResponseEntity<Object> appUserNotExistException(AppUserNotExistException exception){
        return new ResponseEntity<>("User does not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = AppUserEmailTakenException.class)
    public ResponseEntity<Object> appUserEmailTakenException(AppUserEmailTakenException exception){
        return new ResponseEntity<>("User with such email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
