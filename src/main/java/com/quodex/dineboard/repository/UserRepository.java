package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
