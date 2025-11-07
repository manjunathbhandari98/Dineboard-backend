package com.quodex.dineboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequest {
    private String name;
    private String description;
    private double price;
    private String menuId;
    private String categoryId;
    private String itemImage;
}
