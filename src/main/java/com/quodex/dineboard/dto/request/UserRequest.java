package com.quodex.dineboard.dto.request;

import com.quodex.dineboard.enums.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String id;

    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password Required")
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AccountType accountType;
}
