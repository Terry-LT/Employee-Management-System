package com.terylt.employeeManagementSystem.appuser;

import com.terylt.employeeManagementSystem.department.Department;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class AppUserRequest {
    private final String name;
    private final String surname;
    private final String phone;
    private final String email;
    private final LocalDate dob;
    private final String password;
    private final Long department_id;
    private String city;
    private String country;
    private String address;
    private String role;
}
