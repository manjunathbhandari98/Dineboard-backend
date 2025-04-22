package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuItemDTO;

import java.util.List;

public interface MenuItemService {
    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO);

    MenuItemDTO getMenuItemById(Long id);

    List<MenuItemDTO> getAllMenuItems();

    List<MenuItemDTO> getMenuItemsByMenuId(Long menuId, Long categoryId);

    MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO);

    void deleteMenuItem(Long id);
}
