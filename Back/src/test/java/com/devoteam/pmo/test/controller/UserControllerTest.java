

package com.devoteam.pmo.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Assert;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.devoteam.pmo.controller.UserController;
import com.devoteam.pmo.entity.Role;
import com.devoteam.pmo.entity.User;
import com.devoteam.pmo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("unused")
public class UserControllerTest {

    @Mock
    private UserService userService;
    
    private String asJsonString(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        List<User> userList = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER", "User role"));

        userList.add(new User("user1", "password1", roles));
        userList.add(new User("user2", "password2", roles));
        when(userService.getAllUsers()).thenReturn(userList);

        // Act & Assert
        mockMvc.perform(get("/allUsers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(userList.size()));
    }


    @Test
    public void testCreateUserWithRole() throws Exception {
        // Arrange
        User user = new User();
        String roleName = "ROLE_USER";
        when(userService.createUserWithRole(any(User.class), eq(roleName))).thenReturn(user);

        // Act & Assert
        mockMvc.perform(post("/createUserWithRole")
                .content(asJsonString(user))
                .param("roleName", roleName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        String userName = "user1";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER", "User role"));
        User deletedUser = new User(userName, "password1", roles);
        when(userService.deleteUser(userName)).thenReturn(deletedUser);

        // Act & Assert
        mockMvc.perform(delete("/deleteUser/{userName}", userName))
                .andExpect(status().isOk());
    }

}
