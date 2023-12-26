package com.terylt.employeeManagementSystem.appuser;

import com.terylt.employeeManagementSystem.appuser.exception.AppUserEmailTakenException;
import com.terylt.employeeManagementSystem.appuser.exception.AppUserNameTakenException;
import com.terylt.employeeManagementSystem.appuser.exception.AppUserNotExistException;
import com.terylt.employeeManagementSystem.appuser.exception.PhoneNumberNotValidException;
import com.terylt.employeeManagementSystem.department.Department;
import com.terylt.employeeManagementSystem.department.DepartmentRepository;
import com.terylt.employeeManagementSystem.department.exception.DepartmentNotExistException;
import com.terylt.employeeManagementSystem.email.exception.EmailNotValidException;
import com.terylt.employeeManagementSystem.appuser.token.ConfirmationTokenService;
import com.terylt.employeeManagementSystem.appuser.validators.EmailValidator;
import com.terylt.employeeManagementSystem.appuser.validators.PhoneNumberValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with username %s not found";
    private final AppUserRepository appUserRepository;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    //Validators
    private final EmailValidator emailValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    public Optional<AppUser> getAppUserById(Long appUserId){
        Optional<AppUser> appUser = appUserRepository.findById(appUserId);
        return appUser;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByName(username)
                .orElseThrow(()->new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG,username)
                ));
    }
    public List<AppUser> getAllAppUsers(){
        return appUserRepository.findAll();
    }
    public String addUser(AppUserRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        boolean isPhoneNumberValid = phoneNumberValidator.test(request.getPhone());
        //Check if department exists
        //TODO: Refactor it. Do not repeat

        Optional<Department> department = departmentRepository.findById(request.getDepartment_id());
        if (department.isEmpty()){
            throw new DepartmentNotExistException();
        }

        if (!isValidEmail){
            //Throw custom EmailNotValidException
            throw new EmailNotValidException();
        }
        if (!isPhoneNumberValid){
            //Throw custom PhoneNumberNotValidException
            throw new PhoneNumberNotValidException();
        }
        AppUser appUser = null;
        //Set user roles
        //Without creating token
        //TODO: Refactor it. Do not repeat
        if (request.getRole().equals("staff")){
            appUser =
                    new AppUser(
                            AppUserRole.STAFF,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            request.getPassword(),
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );

        }
        if (request.getRole().equals("user")){
            appUser =
                    new AppUser(
                            AppUserRole.USER,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            request.getPassword(),
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );
        }
        if (request.getRole().equals("admin")){
            appUser =
                    new AppUser(
                            AppUserRole.ADMIN,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            request.getPassword(),
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );
        }
        //Check if there is a user with the same name or email
        boolean userWithSuchNameExists = appUserRepository.findByName(appUser.getName()).isPresent();
        boolean userWithSuchEmailExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userWithSuchNameExists){
            //Custom exception AppUserNameTakenException
            throw new AppUserNameTakenException();
        }
        if (userWithSuchEmailExists){
            //Custom exception AppUserEmailTakenException
            throw new AppUserEmailTakenException();
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        //appUserRepository.save(appUser);
        /*String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);*/
        appUserRepository.save(appUser);

        return "User added";
    }
    public String deleteUser(Long appUserId){
        if (!appUserRepository.existsById(appUserId)){
            throw new AppUserNotExistException();
        }
        appUserRepository.deleteById(appUserId);

        return "User was deleted!";

    }
    public String updateUser(Long appUserId,AppUserRequest request){
        //Check if deparment exists
        Optional<Department> department = departmentRepository.findById(request.getDepartment_id());
        //TODO: Refactor it. Do not repeat
        if (department.isEmpty()){
            throw new DepartmentNotExistException();
        }
        if (!appUserRepository.existsById(appUserId)){
            throw new AppUserNotExistException();
        }
        AppUser appUser = appUserRepository.getReferenceById(appUserId);
        AppUser newAppUser = null;
        //TODO: Refactor it. Do not repeat

        if (request.getRole().equals("staff")){
            newAppUser =
                    new AppUser(
                            AppUserRole.STAFF,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            appUser.getPassword(),//we do not change password
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );

        }
        if (request.getRole().equals("user")){
            newAppUser =
                    new AppUser(
                            AppUserRole.USER,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            appUser.getPassword(),//we do not change password
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );
        }
        if (request.getRole().equals("admin")){
            newAppUser =
                    new AppUser(
                            AppUserRole.ADMIN,
                            request.getName(),
                            request.getSurname(),
                            request.getPhone(),
                            request.getEmail(),
                            request.getDob(),
                            request.getPassword(),
                            department.get(),
                            request.getCity(),
                            request.getCountry(),
                            request.getAddress()

                    );
        }
        //Check if there is a user with the same name or email
        boolean userWithSuchNameExists = appUserRepository.findByName(newAppUser.getName()).isPresent();
        boolean userWithSuchEmailExists = appUserRepository.findByEmail(newAppUser.getEmail()).isPresent();
        if (userWithSuchNameExists & !appUser.getName().equals(newAppUser.getName())){
            //Custom exception AppUserNameTakenException
            throw new AppUserNameTakenException();
        }
        if (userWithSuchEmailExists & !appUser.getEmail().equals(newAppUser.getEmail())){
            //Custom exception AppUserEmailTakenException
            throw new AppUserEmailTakenException();
        }
        newAppUser.setId(appUserId);
        appUserRepository.save(newAppUser);
        return "User was updated";
    }
    public int enableUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
