package com.terylt.employeeManagementSystem.department;


import com.terylt.employeeManagementSystem.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String name);


}
