package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelDTO.toEntity();
        hotel.setCreatedAt(LocalDateTime.now());
        hotel.setUpdatedAt(LocalDateTime.now());
        return hotelRepository.save(hotel).toDTO();
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found"))
                .toDTO();
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(Hotel::toDTO)
                .toList();
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found"));

        // Update only necessary fields
        existingHotel.setName(hotelDTO.getName());
        existingHotel.setAddress(hotelDTO.getAddress());
        existingHotel.setContactPhone(hotelDTO.getContactPhone());
        existingHotel.setContactEmail(hotelDTO.getContactEmail());
        existingHotel.setLogoUrl(hotelDTO.getLogoUrl());
        existingHotel.setUpdatedAt(LocalDateTime.now());

        return hotelRepository.save(existingHotel).toDTO();
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
