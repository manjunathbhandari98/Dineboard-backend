package com.quodex.dineboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelRequest {
    private String name;
    private String logoUrl;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private String ownerId;
    private String website;
    private String description;
    private String planId;
}
