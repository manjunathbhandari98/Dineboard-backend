package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByMenuId(String menuId);

    @Transactional
    void deleteByMenu(Menu menu);
}
