package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.LoginRequest;
import com.quodex.dineboard.dto.response.LoginResponse;
import com.quodex.dineboard.dto.request.RegisterRequest;
import com.quodex.dineboard.dto.request.UserRequest;

public interface UserService {
    String registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);
    UserRequest getUserByEmail(String email);

}
