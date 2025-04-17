package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.SettingsDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings")
public class Settings {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Hotel hotel;

    private boolean showHotelName;
    private boolean showDineBoardBranding;

    public SettingsDTO toDTO() {
        return SettingsDTO.builder()
                .id(this.id)
                .hotelId(this.hotel != null ? this.hotel.getId() : null)
                .showHotelName(this.showHotelName)
                .showDineBoardBranding(this.showDineBoardBranding)
                .build();
    }
}
