package com.devoteam.pmo.controller;


import com.devoteam.pmo.entity.User;
import com.devoteam.pmo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired

    private UserService userService;

    @PostConstruct

    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }




    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "this accessible for admin only";


    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "this accessible for user only";
    }



    @PostMapping("/createUserWithRole")
    public ResponseEntity<User> createUserWithRole(@RequestBody User userDto, @RequestParam String roleName) {
        User user = userService.createUserWithRole(userDto, roleName);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }



    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity<User> deleteUser(@PathVariable String userName) {
        User deletedUser = userService.deleteUser(userName);
        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser); // Return the deleted user object with 200 status
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user with userName not found
        }
    }


}
