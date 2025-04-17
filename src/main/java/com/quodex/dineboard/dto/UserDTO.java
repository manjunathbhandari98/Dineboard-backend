package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

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

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .accountType(this.accountType)
                .build();
    }
}
