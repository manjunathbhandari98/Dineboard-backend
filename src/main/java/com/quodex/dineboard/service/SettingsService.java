package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.SettingRequest;
import com.quodex.dineboard.dto.response.SettingResponse;

public interface SettingsService {
    SettingResponse createOrUpdateSettings(SettingRequest dto);
    SettingResponse getSettingsByHotel(String hotelId);
}
