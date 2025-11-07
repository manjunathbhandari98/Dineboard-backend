package com.quodex.dineboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "settings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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

}
