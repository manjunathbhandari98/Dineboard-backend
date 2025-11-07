package com.quodex.dineboard.mapper;
import com.quodex.dineboard.dto.request.MenuRequest;
import com.quodex.dineboard.dto.response.MenuResponse;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;

import java.time.LocalDateTime;

public class MenuMapper {

    public static Menu toEntity(MenuRequest request, Hotel hotel) {
        return Menu.builder()
                .title(request.getTitle())
                .hotel(hotel)
                .isPublished(request.isPublished())
                .createdAt(request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now())
                .updatedAt(request.getUpdatedAt() != null ? request.getUpdatedAt() : LocalDateTime.now())
                .viewCount(request.getViewCount())
                .uniqueDeviceCount(request.getUniqueDeviceCount())
                .build();
    }


    public static MenuResponse toResponse(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .title(menu.getTitle())
                .isPublished(menu.isPublished())
                .createdAt(menu.getCreatedAt())
                .updatedAt(menu.getUpdatedAt())
                .viewCount(menu.getViewCount())
                .uniqueDeviceCount(menu.getUniqueDeviceCount())
                .hotel(menu.getHotel() != null
                        ? HotelMapper.toResponse(menu.getHotel())
                        : null)
                .build();
    }
}
