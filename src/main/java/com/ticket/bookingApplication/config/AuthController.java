package com.ticket.bookingApplication.config;

import com.ticket.bookingApplication.dto.LoginRequestDTO;
import com.ticket.bookingApplication.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtServc;

    public AuthController(AuthenticationManager authenticationManager, JWTService jwtServc) {
        this.authenticationManager = authenticationManager;
        this.jwtServc = jwtServc;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.password()
                )
        );
        String token = jwtServc.generateToken(loginRequestDTO.email());

        return ResponseEntity.ok(token);
    }
}
