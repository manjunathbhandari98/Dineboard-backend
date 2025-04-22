package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMenuIdAndCategoryId(Long menuId, Long categoryId);


}
