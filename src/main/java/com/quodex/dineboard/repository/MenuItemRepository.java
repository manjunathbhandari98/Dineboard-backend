package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMenuIdAndCategoryId(String menuId, Long categoryId);

    @Transactional
    void deleteByMenu(Menu menu);

    List<MenuItem> findByMenuId(String menuId);

}
