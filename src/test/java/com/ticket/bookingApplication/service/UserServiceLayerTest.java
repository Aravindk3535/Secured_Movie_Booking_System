package com.ticket.bookingApplication.service;

import com.ticket.bookingApplication.dto.UserResponseDTO;
import com.ticket.bookingApplication.exception.ResourceNotFoundException;
import com.ticket.bookingApplication.model.User;
import com.ticket.bookingApplication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceLayerTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceLayer userService;

    private User user;

    @BeforeEach
    void setUserService() {
        user = new User();
        user.setFirstName("Aravind");
        user.setLastName("Vardhan");
        user.setName(user.getFirstName() + " " + user.getLastName());
        user.setAge(26);
        user.setRole("user");
        user.setEmail("aravindk3535@gmail.com");
    }

    @Test
    void shouldGetUserById() {

        when(userRepo.findById(1L))
                .thenReturn(Optional.of(user));
        UserResponseDTO result = userService.getUserById(1L);

        assertEquals("Aravind Vardhan", result.getUserName());
        assertEquals("aravindk3535@gmail.com", result.getEmail());

        verify(userRepo).findById(1L);
    }

    @Test
    void shouldGetExceptionWhenUserIdNotFound() {

        when(userRepo.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L)
        );

        assertEquals("User by this Id is not found 1", exception.getMessage());
        verify(userRepo).findById(1L);
    }

    @Test
    void shouldGetExceptionForEmail() {

        when(userRepo.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(user)
        );

        assertEquals( "This email is already registered, trying using another email.", exception.getMessage());
        verify(userRepo).findByEmail(user.getEmail());
    }

    @Test
    void shouldCheckCreateUserWithEncodedPassword() {

        when(userRepo.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(user.getPassword()))
                .thenReturn("hashedPassword123");

        String result = userService.createUser(user);

        assertEquals("User saved successfully", result);

        ArgumentCaptor<User> captor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepo).save(captor.capture());

        User savedUser = captor.getValue();

        assertEquals("hashedPassword123", savedUser.getPassword());
    }

    @Test
    void shouldDeleteUser() {
        when(userRepo.findById(user.getUserId()))
                .thenReturn(Optional.of(user));

        String result = userService.deleteUser(user.getUserId());

        assertEquals("User deleted successfully", result);

        verify(userRepo).deleteById(user.getUserId());
    }
}