package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
