package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.HotelDTO;
import com.quodex.dineboard.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin
@RequiredArgsConstructor
public class HotelController {

    @Autowired
    private HotelService hotelService;


    // Now takes ownerId instead of User
    @PostMapping("/{ownerId}")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO, @PathVariable Long ownerId) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDTO, ownerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<HotelDTO> getHotelByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(hotelService.getHotelByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
