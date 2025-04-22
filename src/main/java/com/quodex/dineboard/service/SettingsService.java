package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.SettingsDTO;

public interface SettingsService {
    SettingsDTO createOrUpdateSettings(SettingsDTO dto);
    SettingsDTO getSettingsByHotel(Long hotelId);
}
