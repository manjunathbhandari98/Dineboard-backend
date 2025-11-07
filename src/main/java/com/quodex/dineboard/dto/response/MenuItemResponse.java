package com.quodex.dineboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private String itemImage;
    private MenuResponse menu;
    private CategoryResponse category;
}
