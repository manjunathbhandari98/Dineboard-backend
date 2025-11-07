package com.quodex.dineboard.repository;

import com.quodex.dineboard.dto.response.HotelResponse;
import com.quodex.dineboard.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, String> {
    Optional<Hotel> findByOwnerId(String ownerId);
}
