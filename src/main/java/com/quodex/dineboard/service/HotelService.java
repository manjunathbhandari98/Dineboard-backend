package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;

import java.util.List;

public interface HotelService {

    // Change: Accept ownerId (Long) instead of User, to fetch User in the implementation
    HotelDTO createHotel(HotelDTO hotelDTO, Long ownerId);

    HotelDTO getHotelById(Long id);

    List<HotelDTO> getAllHotels();

    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);

    void deleteHotel(Long id);

    HotelDTO getHotelByUserId(Long userId);
}
