package com.quodex.dineboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quodex.dineboard.dto.response.HotelResponse;
import com.quodex.dineboard.dto.request.HotelRequest;
import com.quodex.dineboard.service.HotelService;
import lombok.RequiredArgsConstructor;
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

        private final HotelService hotelService;

        //  Create a new hotel
        @PostMapping("/{ownerId}")
        public ResponseEntity<HotelResponse> createHotel(
                @PathVariable String ownerId,
                @RequestPart("hotel") String hotelJson,
                @RequestPart(value = "image", required = false) MultipartFile file
        ) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HotelRequest request = objectMapper.readValue(hotelJson, HotelRequest.class);

                HotelResponse response = hotelService.createHotel(request, ownerId, file);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);

            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid hotel JSON: " + e.getMessage());
            }
        }

        // Get hotel by ID
        @GetMapping("/{id}")
        public ResponseEntity<HotelResponse> getHotel(@PathVariable String id) {
            HotelResponse response = hotelService.getHotelById(id);
            return ResponseEntity.ok(response);
        }

        //  Get hotel by User ID
        @GetMapping("/by-user/{userId}")
        public ResponseEntity<HotelResponse> getHotelByUser(@PathVariable String userId) {
            HotelResponse response = hotelService.getHotelByUserId(userId);
            return ResponseEntity.ok(response);
        }

        //  Get all hotels
        @GetMapping
        public ResponseEntity<List<HotelResponse>> getAllHotels() {
            List<HotelResponse> hotels = hotelService.getAllHotels();
            return ResponseEntity.ok(hotels);
        }

        //  Update hotel
        @PutMapping("/{id}")
        public ResponseEntity<HotelResponse> updateHotel(
                @PathVariable String id,
                @RequestPart("hotel") String hotelJson,
                @RequestPart(value = "image", required = false) MultipartFile file
        ) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HotelRequest request = objectMapper.readValue(hotelJson, HotelRequest.class);

                HotelResponse updated = hotelService.updateHotel(id, request, file);
                return ResponseEntity.ok(updated);

            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid hotel JSON: " + e.getMessage());
            }
        }

        // Delete hotel
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteHotel(@PathVariable String id) {
            hotelService.deleteHotel(id);
            return ResponseEntity.noContent().build();
        }

        // Subscribe hotel to a plan
        @PutMapping("/{id}/subscribe")
        public ResponseEntity<HotelResponse> subscribeToPlan(
                @PathVariable String id,
                @RequestParam String planId
        ) {
            HotelResponse response = hotelService.updatePlan(id, planId);
            return ResponseEntity.ok(response);
        }
}
