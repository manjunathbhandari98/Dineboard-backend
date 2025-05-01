package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;

public class MenuDTO {

    private String id;
    private String title;
    private Long hotelId;
    private boolean isPublished;

    private int viewCount;
    private int uniqueDeviceCount;

    public MenuDTO() {}

    public MenuDTO(String id, String title, Long hotelId, boolean isPublished, int viewCount, int uniqueDeviceCount) {
        this.id = id;
        this.title = title;
        this.hotelId = hotelId;
        this.isPublished = isPublished;
        this.viewCount = viewCount;
        this.uniqueDeviceCount = uniqueDeviceCount;
    }

    public Menu toEntity(Hotel hotel) {
        return new Menu(
                this.id,
                this.title,
                hotel,
                this.isPublished,
                null,
                null,
                this.viewCount,
                this.uniqueDeviceCount
        );
    }

    /*Getters and Setters*/
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
