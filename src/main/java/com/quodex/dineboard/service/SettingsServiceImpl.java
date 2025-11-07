package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.SettingMapper;
import com.quodex.dineboard.dto.request.SettingRequest;
import com.quodex.dineboard.dto.response.SettingResponse;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Settings;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;
    private final HotelRepository hotelRepository;

    @Override
    public SettingResponse createOrUpdateSettings(SettingRequest dto) {
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + dto.getHotelId()));

        Settings settings = settingsRepository.findByHotelId(hotel.getId())
                .orElse(SettingMapper.toEntity(dto, hotel));

        // Update fields
        settings.setEnableNotification(dto.isEnableNotification());
        settings.setDarkModeEnabled(dto.isDarkModeEnabled());
        settings.setBorderAroundQR(dto.isBorderAroundQR());
        settings.setShowHotelName(dto.isShowHotelName());
        settings.setShowDineBoardBranding(dto.isShowDineBoardBranding());
        settings.setHotel(hotel);

        settings = settingsRepository.save(settings);
        return SettingMapper.toResponse(settings);
    }

    @Override
    public SettingResponse getSettingsByHotel(String hotelId) {
        Settings settings = settingsRepository.findByHotelId(hotelId)
                .orElseThrow(() -> new RuntimeException("Settings not found for hotel ID: " + hotelId));
        return SettingMapper.toResponse(settings);
    }
}
