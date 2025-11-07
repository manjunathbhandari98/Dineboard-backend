package com.quodex.dineboard.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettingResponse {
    private String settingId;
    private HotelResponse hotelResponse;
    private boolean enableNotification;
    private boolean darkModeEnabled;
    private boolean borderAroundQR;
    private boolean showHotelName;
    private boolean showDineBoardBranding;
}