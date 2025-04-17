package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.MenuItemDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menu_items")
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ManyToOne
    private Menu menu;

    public MenuItemDTO toDTO() {
        return MenuItemDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .menuId(this.menu != null ? this.menu.getId() : null)
                .build();
    }
}

