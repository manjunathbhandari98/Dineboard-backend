package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.request.LoginRequest;
import com.quodex.dineboard.dto.response.LoginResponse;
import com.quodex.dineboard.dto.request.RegisterRequest;
import com.quodex.dineboard.dto.request.UserRequest;
import com.quodex.dineboard.jwt.JwtUtil;
import com.quodex.dineboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok( userService.registerUser(request));  // Register the user
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserRequest> profile(@RequestHeader("Authorization") String token) {
        // Remove the "Bearer " prefix from the token if it exists
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract username (email) from the JWT token
        String email = jwtUtil.extractUsername(token);

        // Return user profile based on the extracted email
        return ResponseEntity.ok( userService.getUserByEmail(email));
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtUtil.extractUsername(token);

        // Simply check if token is not expired — you can remove the user lookup unless you need to validate against user details
        return !jwtUtil.isTokenExpired(token);
    }


}
