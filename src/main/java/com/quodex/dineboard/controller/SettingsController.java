package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.SettingsDTO;
import com.quodex.dineboard.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @PostMapping
    public SettingsDTO createOrUpdateSettings(@RequestBody SettingsDTO dto) {
        return settingsService.createOrUpdateSettings(dto);
    }

    @GetMapping("/hotel/{hotelId}")
    public SettingsDTO getSettingsByHotel(@PathVariable Long hotelId) {
        return settingsService.getSettingsByHotel(hotelId);
    }
}
