package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);
    UserDTO getUserByEmail(String email);
    UserDTO updateUserPlan(Long userId, Integer planId);

}
