package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;

import java.util.List;

public interface HotelService {

    public HotelDTO createHotel(HotelDTO hotelDTO);

    public HotelDTO getHotelById(Long id);

    public List<HotelDTO> getAllHotels();

    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO);

    public void deleteHotel(Long id);
}
