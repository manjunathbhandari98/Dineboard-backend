package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.MenuRequest;
import com.quodex.dineboard.dto.response.MenuResponse;
import com.quodex.dineboard.exception.DineBoardException;

import java.util.List;

public interface MenuService {

    MenuResponse createMenu(MenuRequest request) throws DineBoardException;

    MenuResponse getMenuById(String id);

    List<MenuResponse> getAllMenus();

    MenuResponse updateMenu(String id, MenuRequest request);

    void deleteMenu(String id);

    List<MenuResponse> getMenusByHotel(String hotelId);

    void trackMenuView(String id);
}
