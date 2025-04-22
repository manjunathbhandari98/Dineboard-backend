package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories(Long menuId);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    void deleteById(Long id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);
}
