package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.MenuItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long menuId;

    public MenuItem toEntity() {
        return MenuItem.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .build();
    }
}
