package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.request.SettingRequest;
import com.quodex.dineboard.dto.response.SettingResponse;
import com.quodex.dineboard.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SettingsController {

    private final SettingsService settingsService;

    @PostMapping
    public ResponseEntity<SettingResponse> createOrUpdateSettings(@RequestBody SettingRequest dto) {
        return ResponseEntity.ok(settingsService.createOrUpdateSettings(dto));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<SettingResponse> getSettingsByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(settingsService.getSettingsByHotel(hotelId));
    }
}
