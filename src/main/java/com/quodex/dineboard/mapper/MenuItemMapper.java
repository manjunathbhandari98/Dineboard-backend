package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.MenuItemRequest;
import com.quodex.dineboard.dto.response.MenuItemResponse;
import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuItem;

public class MenuItemMapper {
    public static MenuItem toEntity(MenuItemRequest request, Menu menu, Category category) {
        return MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .menu(menu)
                .category(category)
                .itemImage(request.getItemImage())
                .build();
    }

    public static MenuItemResponse toResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .itemImage(menuItem.getItemImage())
                .menu(menuItem.getMenu() != null
                        ? MenuMapper.toResponse(menuItem.getMenu())
                        : null)
                .category(menuItem.getCategory() != null
                        ? CategoryMapper.toResponse(menuItem.getCategory())
                        : null)
                .build();
    }
}
