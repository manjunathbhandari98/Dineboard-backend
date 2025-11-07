package com.quodex.dineboard.dto.request;

import com.quodex.dineboard.enums.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password Required")
    private String password;
    private AccountType accountType;
}
