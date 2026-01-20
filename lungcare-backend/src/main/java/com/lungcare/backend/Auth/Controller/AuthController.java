package com.lungcare.backend.Auth.Controller;

import com.lungcare.backend.Security.JWT.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwTutil;

  //  @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        String token = jwTutil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    static class LoginRequest
    {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }

    static class AuthResponse
    {
        private String token;

        public AuthResponse(String token)
        {
            this.token = token;
        }
        public String getToken() { return token; }

    }
}
