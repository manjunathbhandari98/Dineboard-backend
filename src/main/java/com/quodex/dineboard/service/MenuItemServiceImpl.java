package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuItemDTO;
import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuItem;
import com.quodex.dineboard.repository.CategoryRepository;
import com.quodex.dineboard.repository.MenuItemRepository;
import com.quodex.dineboard.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService{

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private byte[] decodeBase64Image(String base64) {
        if (base64.contains(",")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        return Base64.getDecoder().decode(base64);
    }

    public MenuItemDTO createMenuItem(MenuItemDTO dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setMenu(menu);
        item.setCategory(category);
        if (dto.getItemImage() != null && !dto.getItemImage().isEmpty()) {
            byte[] decodedBytes = decodeBase64Image(dto.getItemImage());
            item.setItemImage(dto.getItemImage()); // Optional: if you're storing raw Base64 too
            item.setItemImageBytes(decodedBytes);  // This is the important part
        }
        return menuItemRepository.save(item).toDTO();
    }

    public MenuItemDTO getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"))
                .toDTO();
    }

    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(MenuItem::toDTO)
                .collect(Collectors.toList());
    }

    public List<MenuItemDTO> getMenuItemsByMenuAndCategoryId(String menuId, Long categoryId) {
        return menuItemRepository.findByMenuIdAndCategoryId(menuId, categoryId).stream()
                .map(MenuItem::toDTO)
                .collect(Collectors.toList());
    }

    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());

        if (!item.getMenu().getId().equals(dto.getMenuId())) {
            Menu newMenu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            item.setMenu(newMenu);
        }

        if (!item.getCategory().getId().equals(dto.getCategoryId())) {
            Category newCategory = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(newCategory);
        }

        if (dto.getItemImage() != null && !dto.getItemImage().isEmpty()) {
            byte[] decodedBytes = decodeBase64Image(dto.getItemImage());
            item.setItemImage(dto.getItemImage()); // Optional: if you're storing raw Base64 too
            item.setItemImageBytes(decodedBytes);  // This is the important part
        }

        return menuItemRepository.save(item).toDTO();
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByMenuId(String menuId) {
        return menuItemRepository.findByMenuId(menuId).stream()
                .map(MenuItem::toDTO)
                .collect(Collectors.toList());
    }



}
