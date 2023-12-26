package com.terylt.employeeManagementSystem.department;

import com.terylt.employeeManagementSystem.appuser.AppUser;
import com.terylt.employeeManagementSystem.appuser.AppUserRequest;
import com.terylt.employeeManagementSystem.department.exception.DepartmentNameCannotBeEmptyException;
import com.terylt.employeeManagementSystem.department.exception.DepartmentNameTakenException;
import com.terylt.employeeManagementSystem.department.exception.DepartmentNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    public String addDepartment(DepartmentRequest request){
        if (request.getName() == null){
            throw new DepartmentNameCannotBeEmptyException();
        }
        if (request.getName().isEmpty() ||	request.getName().isBlank()
                ||	request.getName() == null){
            //Throw DepartmentNameCannotBeEmptyException
            throw new DepartmentNameCannotBeEmptyException();
        }
        //Check if department with such name already exists
        boolean departmentWithSuchNameExists = departmentRepository.findByName(request.getName()).isPresent();
        if (departmentWithSuchNameExists){
            //Throw custom exception DepartmentNameTakenException
            throw new DepartmentNameTakenException();
        }
        Department department = new Department(
                request.getName()
        );
        departmentRepository.save(department);
        return "Department was added!";
    }
    public Optional<Department> getDepartmentById(Long id){
        return departmentRepository.findById(id);
    }
    public String deleteDepartmentById(Long id){
        if (departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);

        }
        else{
            throw new DepartmentNotExistException();
        }
        return "The department was deleted";
    }
    public String updateDepartment(Long id, DepartmentRequest request){
        Department departmentOriginal = departmentRepository.getReferenceById(id);
        if (request.getName() == null){
            throw new DepartmentNameCannotBeEmptyException();
        }
        if (request.getName().isEmpty() ||	request.getName().isBlank()
                ||	request.getName() == null){
            //Throw DepartmentNameCannotBeEmptyException
            throw new DepartmentNameCannotBeEmptyException();
        }
        //Check if department with such name already exists
        boolean departmentWithSuchNameExists = departmentRepository.findByName(
                request.getName()).isPresent();
        if (departmentWithSuchNameExists
                &
                !request.getName().equals(departmentOriginal.getName())){
            //Throw custom exception DepartmentNameTakenException
            throw new DepartmentNameTakenException();
        }
        Department departmentNew = new Department(
                request.getName()
        );
        departmentNew.setId(departmentOriginal.getId());
        departmentRepository.save(departmentNew);
        return "Department was updated!";
    }
}
