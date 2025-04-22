package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.UserDTO;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.model.User;
import com.quodex.dineboard.repository.PlanRepository;
import com.quodex.dineboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlanRepository planRepository;


    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        Plan plan = null;
        if (userDTO.getPlanId() != null) {
            plan = planRepository.findById(userDTO.getPlanId())
                    .orElseThrow(() -> new RuntimeException("Invalid plan ID"));
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setCreatedAt(LocalDateTime.now());
        userDTO.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(userDTO.toEntity(plan));
        return savedUser.toDTO();
    }


    @Override
    public UserDTO loginUser(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user.toDTO();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDTO updateUserPlan(Long userId, Long planId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        user.setPlan(plan);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user).toDTO();
    }
}
