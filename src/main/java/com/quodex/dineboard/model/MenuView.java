package com.quodex.dineboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class MenuView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuId;

    private LocalDateTime createdAt;

    public MenuView() {}

    public MenuView(String menuId,  LocalDateTime createdAt) {
        this.menuId = menuId;
        this.createdAt = createdAt;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    public LocalDateTime getViewedAt() {
        return createdAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.createdAt = createdAt;
    }
}
