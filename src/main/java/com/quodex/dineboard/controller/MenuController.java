package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.request.MenuRequest;
import com.quodex.dineboard.dto.response.MenuResponse;
import com.quodex.dineboard.exception.DineBoardException;
import com.quodex.dineboard.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest menuDTO) throws DineBoardException {
        return new ResponseEntity<MenuResponse>(menuService.createMenu(menuDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenu(@PathVariable String id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<MenuResponse>> getMenusByHotel(@PathVariable String hotelId) {
        List<MenuResponse> menus = menuService.getMenusByHotel(hotelId);
        return ResponseEntity.ok(menus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable String id, @RequestBody MenuRequest menuDTO) {
        return ResponseEntity.ok(menuService.updateMenu(id, menuDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("analytics/{id}/track-view")
    public ResponseEntity<Void> trackMenuView(
            @PathVariable String id) {
        menuService.trackMenuView(id);
        return ResponseEntity.ok().build();
    }

}
