package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.MenuViewRequest;
import com.quodex.dineboard.dto.response.MenuViewResponse;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuView;

import java.time.LocalDateTime;

public class MenuViewMapper {
    public static MenuView toEntity(MenuViewRequest request, Menu menu){
        return MenuView.builder()
                .menu(menu)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MenuViewResponse toResponse(MenuView menuView){
        return MenuViewResponse.builder()
                .id(menuView.getId())
                .menu(MenuMapper.toResponse(menuView.getMenu()))
                .createdAt(menuView.getCreatedAt())
                .build();
    }
}
