package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.CategoryRequest;
import com.quodex.dineboard.dto.response.CategoryResponse;
import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request, Menu menu){
        return Category.builder()
                .name(request.getCategoryName())
                .menu(menu)
                .build();
    }

    public static CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .menu(category.getMenu() != null
                ? MenuMapper.toResponse(category.getMenu()) : null)

                .build();
    }
}
