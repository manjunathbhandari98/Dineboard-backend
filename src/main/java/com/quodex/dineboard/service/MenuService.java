package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuDTO;
import com.quodex.dineboard.exception.DineBoardException;

import java.util.List;

public interface MenuService {

    MenuDTO createMenu(MenuDTO menuDTO) throws DineBoardException;

    MenuDTO getMenuById(String id);

    List<MenuDTO> getAllMenus();

    MenuDTO updateMenu(String id, MenuDTO menuDTO);

    void deleteMenu(String id);

    List<MenuDTO> getMenusByHotel(Long hotelId);

    void trackMenuView(String id);
}
