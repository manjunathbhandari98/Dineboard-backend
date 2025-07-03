package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelService {

    // Change: Accept ownerId (Long) instead of User, to fetch User in the implementation
    HotelDTO createHotel(HotelDTO hotelDTO, Long ownerId, MultipartFile file);

    HotelDTO getHotelById(Long id);

    List<HotelDTO> getAllHotels();

    HotelDTO updateHotel(Long id, HotelDTO hotelDTO, MultipartFile file);

    void deleteHotel(Long id);

    HotelDTO getHotelByUserId(Long userId);

    HotelDTO updatePlan(Long id, Integer planId);
}
