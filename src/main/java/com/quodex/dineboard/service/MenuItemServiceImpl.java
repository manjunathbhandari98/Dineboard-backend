package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.MenuItemMapper;
import com.quodex.dineboard.dto.request.MenuItemRequest;
import com.quodex.dineboard.dto.response.MenuItemResponse;
import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuItem;
import com.quodex.dineboard.repository.CategoryRepository;
import com.quodex.dineboard.repository.MenuItemRepository;
import com.quodex.dineboard.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    // ✅ Create new menu item
    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest dto, MultipartFile file) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            dto.setItemImage(imgUrl);
        }

        MenuItem item = MenuItemMapper.toEntity(dto, menu, category);
        item = menuItemRepository.save(item);

        return MenuItemMapper.toResponse(item);

    }

    // ✅ Get single menu item by ID
    @Override
    public MenuItemResponse getMenuItemById(String id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        return MenuItemMapper.toResponse(menuItem);
    }

    // ✅ Get all menu items
    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    // ✅ Get menu items by both menu & category
    @Override
    public List<MenuItemResponse> getMenuItemsByMenuAndCategoryId(String menuId, String categoryId) {
        return menuItemRepository.findByMenuIdAndCategoryId(menuId, categoryId)
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    // ✅ Update menu item
    @Override
    public MenuItemResponse updateMenuItem(String id, MenuItemRequest dto, MultipartFile file) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        // Basic field updates
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());

        // Update menu if changed
        if (!item.getMenu().getId().equals(dto.getMenuId())) {
            Menu newMenu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            item.setMenu(newMenu);
        }

        // Update category if changed
        if (!item.getCategory().getId().equals(dto.getCategoryId())) {
            Category newCategory = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(newCategory);
        }

        // Upload and replace image if provided
        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            item.setItemImage(imgUrl);
        } else if (dto.getItemImage() != null) {
            item.setItemImage(dto.getItemImage());
        }

        item = menuItemRepository.save(item);
        return MenuItemMapper.toResponse(item);
    }

    // ✅ Delete menu item
    @Override
    public void deleteMenuItem(String id) {
        if (!menuItemRepository.existsById(id)) {
            throw new RuntimeException("Menu item not found");
        }
        menuItemRepository.deleteById(id);
    }

    // ✅ Get menu items by menu ID
    @Override
    public List<MenuItemResponse> getMenuItemsByMenuId(String menuId) {
        return menuItemRepository.findByMenuId(menuId)
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }
}
