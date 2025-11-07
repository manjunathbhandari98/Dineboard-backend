package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.response.HotelResponse;
import com.quodex.dineboard.dto.request.HotelRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface HotelService {

    // Create a new hotel
    HotelResponse createHotel(HotelRequest request, String ownerId, MultipartFile file);

    // Get hotel by ID
    HotelResponse getHotelById(String id);

    // Get all hotels
    List<HotelResponse> getAllHotels();

    // Update existing hotel
    HotelResponse updateHotel(String id, HotelRequest request, MultipartFile file);

    // Delete hotel
    void deleteHotel(String id);

    // Get hotel by user ID
    HotelResponse getHotelByUserId(String userId);

    // Subscribe hotel to a plan
    HotelResponse updatePlan(String id, String planId);
}

