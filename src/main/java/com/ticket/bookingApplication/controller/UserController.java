package com.ticket.bookingApplication.controller;

import com.ticket.bookingApplication.dto.UserResponseDTO;
import com.ticket.bookingApplication.model.User;
import com.ticket.bookingApplication.service.UserServiceLayer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceLayer userService;
    public UserController(UserServiceLayer userService){
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/createAccount")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/editUser/{id}")
    public String editUser(@PathVariable Long id, @RequestBody User user) {
        return userService.editUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/")
    public String welcome(){
        return "Welcome to ticket booking application with updated version";
    }
}
