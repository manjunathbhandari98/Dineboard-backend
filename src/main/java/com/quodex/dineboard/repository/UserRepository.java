package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // Find user by email (used during login)
    Optional<User> findByEmail(String email);

    // Check if a user with a given email already exists
    boolean existsByEmail(String email);

}
