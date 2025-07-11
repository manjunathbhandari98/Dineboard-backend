package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.MenuItemDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    private String itemImage;


    public MenuItem() {}

    public MenuItem(Long id, String name, String description, double price, Menu menu, Category category, String itemImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.menu = menu;
        this.category = category;
        this.itemImage = itemImage;
    }

    public MenuItemDTO toDTO() {

        return new MenuItemDTO(
                this.id,
                this.name,
                this.description,
                this.price,
                this.menu != null ? this.menu.getId() : null,
                this.category != null ? this.category.getId() : null , // Add category info to DTO
                this.itemImage
        );
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

}
