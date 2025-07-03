package com.quodex.dineboard.dto;

public class MenuItemDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String menuId;  // Assuming you want to include the associated Menu ID
    private Long categoryId;  // Add categoryId to include category information

    private String itemImage;

    public MenuItemDTO(){}

    public MenuItemDTO(Long id, String name, String description, double price, String menuId, Long categoryId, String itemImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.menuId = menuId;
        this.categoryId = categoryId;
        this.itemImage = itemImage;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

}
