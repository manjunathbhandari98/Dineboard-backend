package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
