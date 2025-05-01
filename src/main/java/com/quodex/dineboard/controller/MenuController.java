package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.MenuDTO;
import com.quodex.dineboard.exception.DineBoardException;
import com.quodex.dineboard.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(@RequestBody MenuDTO menuDTO) throws DineBoardException {
        return new ResponseEntity<MenuDTO>(menuService.createMenu(menuDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable String id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<MenuDTO>> getMenusByHotel(@PathVariable Long hotelId) {
        List<MenuDTO> menus = menuService.getMenusByHotel(hotelId);
        return ResponseEntity.ok(menus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable String id, @RequestBody MenuDTO menuDTO) {
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
