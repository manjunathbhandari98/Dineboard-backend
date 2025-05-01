package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.MenuDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "menus")
public class Menu {

    @Id
    private String id;

    private String title;

    @ManyToOne
    private Hotel hotel;

    private boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(name = "view_count")
    private int viewCount = 0;

    @Column(name = "unique_device_count")
    private int uniqueDeviceCount = 0;

    public Menu() {
        this.id = UUID.randomUUID().toString(); // auto-generate UUID if created via no-arg constructor
    }

    public Menu(String id, String title, Hotel hotel, boolean isPublished,
                LocalDateTime createdAt, LocalDateTime updatedAt, int viewCount, int uniqueDeviceCount) {
        // if id is null or blank, generate a new UUID
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.title = title;
        this.hotel = hotel;
        this.isPublished = isPublished;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.viewCount = viewCount;
        this.uniqueDeviceCount = uniqueDeviceCount;
    }

    public MenuDTO toDTO() {
        return new MenuDTO(
                this.id,
                this.title,
                this.hotel != null ? this.hotel.getId() : null,
                this.isPublished,
                this.viewCount,
        this.uniqueDeviceCount
        );
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getUniqueDeviceCount() {
        return uniqueDeviceCount;
    }

    public void setUniqueDeviceCount(int uniqueDeviceCount) {
        this.uniqueDeviceCount = uniqueDeviceCount;
    }
}
