package com.terylt.employeeManagementSystem.department;

import com.terylt.employeeManagementSystem.appuser.AppUser;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private Set<AppUser> appUsers;

    public Department(String name) {
        this.name = name;
    }
}
