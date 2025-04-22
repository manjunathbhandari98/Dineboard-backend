package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.User;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (hotelDTO.getLogoUrl() != null && !hotelDTO.getLogoUrl().isEmpty()) {
            hotelDTO.setLogoUrlBytes(decodeBase64Image(hotelDTO.getLogoUrl()));
        }

        Hotel hotel = hotelDTO.toEntity(owner);
        hotel.setCreatedAt(LocalDateTime.now());
        hotel.setUpdatedAt(LocalDateTime.now());

        return hotelRepository.save(hotel).toDTO();
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"))
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
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setContactEmail(hotelDTO.getContactEmail());
        hotel.setContactPhone(hotelDTO.getContactPhone());
        hotel.setWebsite(hotelDTO.getWebsite());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setUpdatedAt(LocalDateTime.now());

        if (hotelDTO.getLogoUrl() != null && !hotelDTO.getLogoUrl().isEmpty()) {
            hotel.setLogoUrlBytes(decodeBase64Image(hotelDTO.getLogoUrl()));
        }

        return hotelRepository.save(hotel).toDTO();
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public HotelDTO getHotelByUserId(Long userId) {
        Hotel hotel = (Hotel) hotelRepository.findByOwnerId(userId)
                .orElseThrow(() -> new RuntimeException("Hotel not found for user ID: " + userId));
        return hotel.toDTO();
    }

    private byte[] decodeBase64Image(String base64) {
        if (base64.contains(",")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        return Base64.getDecoder().decode(base64);
    }
}
