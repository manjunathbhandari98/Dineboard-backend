package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Optional<Settings> findByHotelId(Long id);
}
