package com.terylt.employeeManagementSystem.department.exception;

import com.terylt.employeeManagementSystem.appuser.exception.AppUserNameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepartmentExceptionController {
    @ExceptionHandler(value = DepartmentNameCannotBeEmptyException.class)
    public ResponseEntity<Object> departmentNameCannotBeEmptyException(){
        return new ResponseEntity<>("Department name field cannot be empty!",
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value = DepartmentNotExistException.class)
    public ResponseEntity<Object> departmentNotExistException(){
        return new ResponseEntity<>("Department does not exist!",
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value = DepartmentNameTakenException.class)
    public ResponseEntity<Object> departmentNameTakenException(){
        return new ResponseEntity<>("Department with name already exist!",
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
