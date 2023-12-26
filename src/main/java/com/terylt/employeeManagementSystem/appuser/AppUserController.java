package com.terylt.employeeManagementSystem.appuser;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/appUser")
public class AppUserController {
    private final AppUserService appUserService;
    @PostMapping("/{appUserId}")
    public Optional<AppUser> getUserById(@PathVariable(value = "appUserId") Long appUserId){
        return appUserService.getAppUserById(appUserId);
    }
    @GetMapping()
    public List<AppUser> getAllAppUsers(){
        return appUserService.getAllAppUsers();
    }
    @PostMapping("/add")
    public String addUser(@RequestBody AppUserRequest request){

        return appUserService.addUser(request);
    }
    @DeleteMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable(value = "appUserId") Long appUserId){
        return appUserService.deleteUser(appUserId);
    }
    @PutMapping("/update/{appUserId}")
    public String updateUser(
            @PathVariable(value = "appUserId") Long appUserId,
            @RequestBody AppUserRequest request){
        return appUserService.updateUser(appUserId,request);
    }
}
