package com.quodex.dineboard.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
