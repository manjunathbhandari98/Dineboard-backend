package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.response.HotelResponse;
import com.quodex.dineboard.dto.request.HotelRequest;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.model.User;


import java.time.LocalDateTime;

public class HotelMapper {

    public static Hotel toEntity(HotelRequest request, User owner, Plan plan) {
        return Hotel.builder()
                .name(request.getName())
                .logoUrl(request.getLogoUrl())
                .address(request.getAddress())
                .contactEmail(request.getContactEmail())
                .contactPhone(request.getContactPhone())
                .website(request.getWebsite())
                .description(request.getDescription())
                .owner(owner)
                .plan(plan)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static HotelResponse toResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .logoUrl(hotel.getLogoUrl())
                .address(hotel.getAddress())
                .contactEmail(hotel.getContactEmail())
                .contactPhone(hotel.getContactPhone())
                .website(hotel.getWebsite())
                .description(hotel.getDescription())
                .ownerId(hotel.getOwner() != null ? hotel.getOwner().getId() : null)
                .planName(hotel.getPlan() != null ? hotel.getPlan().getName() : null)
                .planId(hotel.getPlan() != null? hotel.getPlan().getId() : null)
                .build();
    }
}
