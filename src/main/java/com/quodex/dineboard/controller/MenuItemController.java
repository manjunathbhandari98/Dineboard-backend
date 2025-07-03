package com.quodex.dineboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quodex.dineboard.dto.MenuItemDTO;
import com.quodex.dineboard.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/menu-item")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public MenuItemDTO createMenuItem(
            @RequestPart("menuItem") String menuItem,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        MenuItemDTO menuItemDTO;
        try{
            // Convert JSON string into MenuItemDTO
            menuItemDTO = objectMapper.readValue(menuItem, MenuItemDTO.class);

            // Pass both menuItem and image to service layer
            return menuItemService.createMenuItem(menuItemDTO, file);
        }
        catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByMenu(@PathVariable String menuId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByMenuId(menuId));
    }

    @GetMapping("/menu/{menuId}/{categoryId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsByMenuAndCategory(@PathVariable String menuId, @PathVariable Long categoryId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByMenuAndCategoryId(menuId, categoryId));
    }

    @PutMapping("/{id}")
    public MenuItemDTO updateMenuItem(
            @PathVariable Long id,
            @RequestPart("menuItem") String menuItem,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        MenuItemDTO menuItemDTO;
        try{
            // Convert JSON string into MenuItemDTO
            menuItemDTO = objectMapper.readValue(menuItem, MenuItemDTO.class);

            // Pass both menuItem and image to service layer
            return menuItemService.updateMenuItem(id, menuItemDTO,                           file);
        }
        catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
