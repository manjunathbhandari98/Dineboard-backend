package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.SettingsDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id", nullable = false, unique = true)
    private Hotel hotel;

    @Column(name = "enable_notification")
    private boolean enableNotification;

    @Column(name = "dark_mode_enabled")
    private boolean darkModeEnabled;

    @Column(name = "border_around_qr")
    private boolean borderAroundQR;

    @Column(name = "show_hotel_name")
    private boolean showHotelName;

    @Column(name = "show_dineboard_branding")
    private boolean showDineBoardBranding;

    public Settings() {}

    public Settings(Long id, Hotel hotel, boolean enableNotification, boolean darkModeEnabled,
                    boolean borderAroundQR, boolean showHotelName, boolean showDineBoardBranding) {
        this.id = id;
        this.hotel = hotel;
        this.enableNotification = enableNotification;
        this.darkModeEnabled = darkModeEnabled;
        this.borderAroundQR = borderAroundQR;
        this.showHotelName = showHotelName;
        this.showDineBoardBranding = showDineBoardBranding;
    }

    public SettingsDTO toDTO() {
        return new SettingsDTO(
                this.id,
                hotel != null ? hotel.getId() : null,
                enableNotification,
                darkModeEnabled,
                borderAroundQR,
                showHotelName,
                showDineBoardBranding
        );
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
