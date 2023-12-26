package com.terylt.employeeManagementSystem.department;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @PostMapping("/add")
    public String addDepartment(@RequestBody DepartmentRequest request){
        return departmentService.addDepartment(request);
    }
    @GetMapping("/{departmentId}")
    public Optional<Department> getDepartmentById(
            @PathVariable(value = "departmentId") Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }
    @PutMapping("/{departmentId}")
    public String updateDepartment(
            @PathVariable(value = "departmentId") Long departmentId,
            @RequestBody DepartmentRequest request){
        return departmentService.updateDepartment(departmentId,request);
    }
    @DeleteMapping("/{departmentId}")
    public String deleteDepartmentById(
            @PathVariable(value = "departmentId") Long departmentId){
        return departmentService.deleteDepartmentById(departmentId);
    }
}
