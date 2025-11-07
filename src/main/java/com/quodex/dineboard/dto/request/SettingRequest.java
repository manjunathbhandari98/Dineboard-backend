package com.quodex.dineboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettingRequest {
    private String hotelId;
    private boolean enableNotification;
    private boolean darkModeEnabled;
    private boolean borderAroundQR;
    private boolean showHotelName;
    private boolean showDineBoardBranding;
}
