package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.MenuItemRequest;
import com.quodex.dineboard.dto.response.MenuItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuItemService {
    MenuItemResponse createMenuItem(MenuItemRequest request, MultipartFile file);

    MenuItemResponse getMenuItemById(String id);

    List<MenuItemResponse> getAllMenuItems();

    List<MenuItemResponse> getMenuItemsByMenuAndCategoryId(String menuId, String categoryId);

    MenuItemResponse updateMenuItem(String id, MenuItemRequest request, MultipartFile file);

    void deleteMenuItem(String id);

    List<MenuItemResponse> getMenuItemsByMenuId(String menuId);

}
