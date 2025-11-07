package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.CategoryRequest;
import com.quodex.dineboard.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories(String menuId);

    CategoryResponse updateCategory(String id, CategoryRequest request);

    CategoryResponse getCategoryById(String id);

    void deleteById(String id);

    CategoryResponse createCategory(CategoryRequest request);
}
