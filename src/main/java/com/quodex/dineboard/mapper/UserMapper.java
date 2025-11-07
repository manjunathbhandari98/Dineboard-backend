package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.RegisterRequest;
import com.quodex.dineboard.dto.request.UserRequest;
import com.quodex.dineboard.jwt.JwtUtil;
import com.quodex.dineboard.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {

    private final JwtUtil jwtUtil;

    public static User toEntity(RegisterRequest request){
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();
    }

    public static UserRequest toResponse(User user){
        return UserRequest.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .accountType(user.getAccountType())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
