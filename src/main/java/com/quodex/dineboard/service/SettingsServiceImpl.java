package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.SettingsDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Settings;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public SettingsDTO createOrUpdateSettings(SettingsDTO dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Settings settings = settingsRepository.findByHotelId(hotel.getId())
                .orElse(new Settings());

        settings.setHotel(hotel);
        settings.setEnableNotification(dto.isEnableNotification());
        settings.setDarkModeEnabled(dto.isDarkModeEnabled());
        settings.setBorderAroundQR(dto.isBorderAroundQR());
        settings.setShowHotelName(dto.isShowHotelName());
        settings.setShowDineBoardBranding(dto.isShowDineBoardBranding());

        return settingsRepository.save(settings).toDTO();
    }

    @Override
    public SettingsDTO getSettingsByHotel(Long hotelId) {
        Settings settings = settingsRepository.findByHotelId(hotelId)
                .orElseThrow(() -> new RuntimeException("Settings not found for hotel"));

        return settings.toDTO();
    }
}
