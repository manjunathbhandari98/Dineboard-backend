package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;

public class MenuDTO {

    private Long id;
    private String title;
    private Long hotelId;
    private boolean isPublished;

    public MenuDTO() {}

    public MenuDTO(Long id, String title, Long hotelId, boolean isPublished) {
        this.id = id;
        this.title = title;
        this.hotelId = hotelId;
        this.isPublished = isPublished;
    }

    public Menu toEntity(Hotel hotel) {
        return new Menu(
                this.id,
                this.title,
                hotel,
                this.isPublished,
                null,
                null
        );
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }
}
