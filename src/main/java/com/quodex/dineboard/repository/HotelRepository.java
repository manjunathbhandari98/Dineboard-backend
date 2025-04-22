package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Object> findByOwnerId(Long ownerId);
}
