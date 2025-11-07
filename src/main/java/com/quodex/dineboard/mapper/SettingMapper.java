package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.SettingRequest;
import com.quodex.dineboard.dto.response.SettingResponse;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Settings;

public class SettingMapper {
    public static Settings toEntity(SettingRequest request, Hotel hotel){
        return Settings.builder()
                .hotel(hotel)
                .borderAroundQR(request.isBorderAroundQR())
                .darkModeEnabled(request.isDarkModeEnabled())
                .enableNotification(request.isEnableNotification())
                .showHotelName(request.isShowHotelName())
                .showDineBoardBranding(request.isShowDineBoardBranding())
                .build();
    }

    public static SettingResponse toResponse(Settings settings){
        return SettingResponse.builder()
                .settingId(settings.getId())
                .hotelResponse(HotelMapper.toResponse(settings.getHotel()))
                .borderAroundQR(settings.isBorderAroundQR())
                .darkModeEnabled(settings.isDarkModeEnabled())
                .enableNotification(settings.isEnableNotification())
                .showHotelName(settings.isShowHotelName())
                .showDineBoardBranding(settings.isShowDineBoardBranding())
                .build();
    }
}
