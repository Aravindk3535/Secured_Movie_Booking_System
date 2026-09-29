package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.UserResponseDTO;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.User;
import com.ticket.bookingApplication.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceLayer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceLayer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User by this Id is not found " + id);
        }
        return new UserResponseDTO(
                user.get().getName(),
                user.get().getEmail()
        );
    }

    public String createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setName(user.getFirstName() + " " + user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setAge(user.getAge());
            newUser.setGender(user.getGender());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
            return "User saved successfully";
        }
        throw new RuntimeException( "This email is already registered, trying using another email.");
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userDTOList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserResponseDTO userDTO = new UserResponseDTO(
                    user.getName(),
                    user.getEmail()
            );
            userDTOList.add(userDTO);
        }
        if (userDTOList.isEmpty()) {
            throw new ResourceNotFoundException("No users available");
        }
        return userDTOList;
    }

    public String editUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            throw new ResourceNotFoundException("User by this Id is not found " + id);
        }
        existingUser.get().setFirstName(user.getFirstName());
        existingUser.get().setLastName(user.getLastName());
        existingUser.get().setName(user.getName());
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setAge(user.getAge());
        existingUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.get().setGender(user.getGender());
        existingUser.get().setRole(user.getRole());
        return "Changes saved successfully";
    }

    public String deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("User by this Id is not found " + id);
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

}
