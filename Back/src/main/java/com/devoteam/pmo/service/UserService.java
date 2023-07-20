package com.devoteam.pmo.service;


import com.devoteam.pmo.entity.Role;
import com.devoteam.pmo.entity.User;
import com.devoteam.pmo.repository.RoleRepository;
import com.devoteam.pmo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private RoleRepository roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private List<User> userList = new ArrayList<>();

    //    public User registerNewUser(User user) {
//        Role role = roleDao.findById("User").get();
//        Set<Role> roles =new HashSet<>();
//        roles.add(role);
//        user.setRole(roles);
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//        return userDao.save(user);
//
//    }
//    public User registerNewUser(User user, Role role) {
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRole(roles);
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//        return userDao.save(user);
//    }

    public User createUserWithRole(User userDto, String roleName) {
        User user = new User();
        user.setUserFirstName(userDto.getUserFirstName());
        user.setUserLastName(userDto.getUserLastName());
        user.setUserName(userDto.getUserName());
        user.setUserPassword(getEncodedPassword(userDto.getUserPassword()));

        Role role = roleDao.findById(roleName).orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);

        return userDao.save(user);
    }



    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void initRolesAndUser() {


        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("devoteamAdmin");
        adminUser.setUserPassword(getEncodedPassword("devoteam123"));
        Set<Role> adminRoles = new HashSet<>();
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserFirstName("ted");
        user.setUserLastName("Amoussou");
        user.setUserName("Teddo");
        user.setUserPassword(getEncodedPassword("teddy123"));
        Set<Role> userRoles = new HashSet<>();
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role");
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);


    }



    public User deleteUser(String userName) {
        User userToDelete = getUserByUserName(userName);
        if (userToDelete != null) {
            userList.remove(userToDelete);
            return userToDelete; // Return the deleted user object
        } else {
            return null; // User with userName not found
        }
    }

    public User getUserByUserName(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null; // User with userName not found
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
