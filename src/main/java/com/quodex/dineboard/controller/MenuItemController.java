package com.quodex.dineboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quodex.dineboard.dto.request.MenuItemRequest;
import com.quodex.dineboard.dto.response.MenuItemResponse;
import com.quodex.dineboard.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<?> createMenuItem(
            @RequestPart("menuItem") String menuItemJson,
            @RequestPart(value = "image", required = false) MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MenuItemRequest menuItemRequest = objectMapper.readValue(menuItemJson, MenuItemRequest.class);
            MenuItemResponse createdItem = menuItemService.createMenuItem(menuItemRequest, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid JSON format", "details", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Server error", "details", e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getMenuItem(@PathVariable String id) {
        MenuItemResponse response = menuItemService.getMenuItemById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems() {
        List<MenuItemResponse> items = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<MenuItemResponse>> getMenuItemsByMenu(@PathVariable String menuId) {
        List<MenuItemResponse> items = menuItemService.getMenuItemsByMenuId(menuId);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/menu/{menuId}/category/{categoryId}")
    public ResponseEntity<List<MenuItemResponse>> getMenuItemsByMenuAndCategory(
            @PathVariable String menuId,
            @PathVariable String categoryId) {
        List<MenuItemResponse> items = menuItemService.getMenuItemsByMenuAndCategoryId(menuId, categoryId);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(
            @PathVariable String id,
            @RequestPart("menuItem") String menuItemJson,
            @RequestPart(value = "image", required = false) MultipartFile file) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            MenuItemRequest menuItemRequest = objectMapper.readValue(menuItemJson, MenuItemRequest.class);
            MenuItemResponse updatedItem = menuItemService.updateMenuItem(id, menuItemRequest, file);
            return ResponseEntity.ok(updatedItem);

        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
