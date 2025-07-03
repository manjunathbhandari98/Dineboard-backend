package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuItemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuItemService {
    MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO, MultipartFile file);

    MenuItemDTO getMenuItemById(Long id);

    List<MenuItemDTO> getAllMenuItems();

    List<MenuItemDTO> getMenuItemsByMenuAndCategoryId(String menuId, Long categoryId);

    MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO, MultipartFile file);

    void deleteMenuItem(Long id);

    List<MenuItemDTO> getMenuItemsByMenuId(String menuId);

}
