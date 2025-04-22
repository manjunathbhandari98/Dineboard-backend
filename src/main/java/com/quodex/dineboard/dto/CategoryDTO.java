package com.quodex.dineboard.dto;

public class CategoryDTO {

    private Long id;
    private String name;
    private Long menuId;

    public CategoryDTO(Long id, String name, Long menuId) {
        this.id = id;
        this.name = name;
        this.menuId = menuId;
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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
