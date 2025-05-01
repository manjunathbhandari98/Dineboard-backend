package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

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


    public UserDTO() {}

    public UserDTO(Long id, String name, String email, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountType = accountType;
    }

    public UserDTO(Long id, String name, String email, String password,
                   LocalDateTime createdAt, LocalDateTime updatedAt, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accountType = accountType;
    }

    public User toEntity() {
        return new User(
                this.id,
                this.name,
                this.email,
                this.password,
                this.createdAt,
                this.updatedAt,
                this.accountType

        );
    }

    // --- Getters and Setters manually written below ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


}
