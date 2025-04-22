package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByHotelId(Long hotelId);
}
