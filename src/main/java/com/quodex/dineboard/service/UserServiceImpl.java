package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.UserMapper;
import com.quodex.dineboard.dto.request.LoginRequest;
import com.quodex.dineboard.dto.response.LoginResponse;
import com.quodex.dineboard.dto.request.RegisterRequest;
import com.quodex.dineboard.dto.request.UserRequest;
import com.quodex.dineboard.jwt.JwtUtil;
import com.quodex.dineboard.model.User;
import com.quodex.dineboard.repository.PlanRepository;
import com.quodex.dineboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlanRepository planRepository;
    private final JwtUtil jwtUtil;

    @Override
    public String registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = UserMapper.toEntity(request);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "Registered Successfully";
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        //  Generate JWT token for the logged-in user
        String token = jwtUtil.generateToken(user.getEmail());

        //  Return LoginResponse with token included
        return LoginResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .jwt(token)
                .build();
    }

    @Override
    public UserRequest getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
