package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.UserDTO;
import com.quodex.dineboard.jwt.JwtUtil;
import com.quodex.dineboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);  // Register the user
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        UserDTO loggedInUser = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        return jwtUtil.generateToken(loggedInUser.getEmail());
    }



    @GetMapping("/profile")
    public UserDTO profile(@RequestHeader("Authorization") String token) {
        // Remove the "Bearer " prefix from the token if it exists
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract username (email) from the JWT token
        String email = jwtUtil.extractUsername(token);

        // Return user profile based on the extracted email
        return userService.getUserByEmail(email);
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
