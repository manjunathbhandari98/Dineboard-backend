package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.MenuView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface MenuViewRepository extends JpaRepository<MenuView, Long> {

    long countByMenuIdAndCreatedAtBetween(
            String menuId, LocalDateTime start, LocalDateTime end);

}
