package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


// DTO
@Getter
@Setter
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String logoUrl;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private Long ownerId;

    // HotelDTO.java
    public  Hotel toEntity(HotelDTO dto, User owner) {
        return Hotel.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .logoUrl(dto.getLogoUrl())
                .contactPhone(dto.getContactPhone())
                .contactEmail(dto.getContactEmail())
                .owner(owner)
                .build();
    }

}

