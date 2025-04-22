package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Settings;

public class SettingsDTO {

    private Long id;
    private Long hotelId;
    private boolean enableNotification;
    private boolean darkModeEnabled;
    private boolean borderAroundQR;
    private boolean showHotelName;
    private boolean showDineBoardBranding;

    public SettingsDTO() {}

    public SettingsDTO(Long id, Long hotelId, boolean enableNotification, boolean darkModeEnabled,
                       boolean borderAroundQR, boolean showHotelName, boolean showDineBoardBranding) {
        this.id = id;
        this.hotelId = hotelId;
        this.enableNotification = enableNotification;
        this.darkModeEnabled = darkModeEnabled;
        this.borderAroundQR = borderAroundQR;
        this.showHotelName = showHotelName;
        this.showDineBoardBranding = showDineBoardBranding;
    }

    public Settings toEntity(Hotel hotel) {
        return new Settings(
                this.id,
                hotel,
                this.enableNotification,
                this.darkModeEnabled,
                this.borderAroundQR,
                this.showHotelName,
                this.showDineBoardBranding
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        this.darkModeEnabled = darkModeEnabled;
    }

    public boolean isBorderAroundQR() {
        return borderAroundQR;
    }

    public void setBorderAroundQR(boolean borderAroundQR) {
        this.borderAroundQR = borderAroundQR;
    }

    public boolean isShowHotelName() {
        return showHotelName;
    }

    public void setShowHotelName(boolean showHotelName) {
        this.showHotelName = showHotelName;
    }

    public boolean isShowDineBoardBranding() {
        return showDineBoardBranding;
    }

    public void setShowDineBoardBranding(boolean showDineBoardBranding) {
        this.showDineBoardBranding = showDineBoardBranding;
    }
}
