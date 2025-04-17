package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Settings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SettingsDTO {
    private Long id;
    private Long hotelId;
    private boolean showHotelName;
    private boolean showDineBoardBranding;

    public Settings toEntity() {
        return Settings.builder()
                .id(this.id)
                .showHotelName(this.showHotelName)
                .showDineBoardBranding(this.showDineBoardBranding)
                .build();
    }
}
