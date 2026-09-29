package com.ticket.bookingApplication.config;

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
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        String token = jwtServc.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }
}
