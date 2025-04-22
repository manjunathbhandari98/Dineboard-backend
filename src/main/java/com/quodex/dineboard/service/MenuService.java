package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    MenuDTO createMenu(MenuDTO menuDTO);

    MenuDTO getMenuById(Long id);

    List<MenuDTO> getAllMenus();

    MenuDTO updateMenu(Long id, MenuDTO menuDTO);

    void deleteMenu(Long id);

    List<MenuDTO> getMenusByHotel(Long hotelId);
}
