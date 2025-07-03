package com.quodex.dineboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quodex.dineboard.dto.HotelDTO;
import com.quodex.dineboard.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDTO createHotel(
            @PathVariable Long ownerId,
            @RequestPart("hotel") String hotelString,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        HotelDTO hotelDTO;

        try {
            // Convert JSON string into HotelDTO
            hotelDTO = objectMapper.readValue(hotelString, HotelDTO.class);

            // Pass both hotel and image to service layer
            return hotelService.createHotel(hotelDTO, ownerId, file);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
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
    public ResponseEntity<HotelDTO> updateHotel(
            @PathVariable Long id,
            @RequestPart("hotel") String hotelString,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        HotelDTO hotelDTO;

        try {
            hotelDTO = objectMapper.readValue(hotelString, HotelDTO.class);
            return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO, file));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/subscribe")
    public HotelDTO subscribeToPlan(@PathVariable Long id, @RequestParam Integer planId) {
        return hotelService.updatePlan(id, planId);
    }
}
