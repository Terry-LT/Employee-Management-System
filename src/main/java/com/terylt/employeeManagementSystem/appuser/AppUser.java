package com.terylt.employeeManagementSystem.appuser;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.terylt.employeeManagementSystem.department.Department;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AppUser implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;
    //Personal info
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String surname;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private LocalDate dob;
    //private Integer age;
    private String password;

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            nullable = true
    )
    private Department department;
    //TODO: Add photo field later
    //Location info
    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private String address;
    //Other
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(AppUserRole appUserRole,
                   String name,
                   String surname,
                   String phone,
                   String email,
                   LocalDate dob,
                   //Integer age,
                   String password,
                   Department department,
                   String city,
                   String country,
                   String address) {
        this.appUserRole = appUserRole;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        //this.age = age;
        this.password = password;
        this.department = department;
        this.city = city;
        this.country = country;
        this.address = address;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
