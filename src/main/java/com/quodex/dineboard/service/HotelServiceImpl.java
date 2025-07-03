package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.HotelDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.model.User;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.PlanRepository;
import com.quodex.dineboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO, Long ownerId, MultipartFile file) {

        // Step 1: Fetch owner by ID or throw error
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Upload logo image to Cloudinary and set URL
        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            hotelDTO.setLogoUrl(imgUrl); // Set uploaded image URL to DTO
        }


        // Step 3: Convert DTO to Entity and set audit fields
        Hotel hotel = hotelDTO.toEntity(owner, null);
        hotel.setCreatedAt(LocalDateTime.now());
        hotel.setUpdatedAt(LocalDateTime.now());

        // Step 4: Save to DB and return as DTO
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
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO, MultipartFile file) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setContactEmail(hotelDTO.getContactEmail());
        hotel.setContactPhone(hotelDTO.getContactPhone());
        hotel.setWebsite(hotelDTO.getWebsite());
        hotel.setDescription(hotelDTO.getDescription());

        // ✅ Upload and update image if provided
        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            hotel.setLogoUrl(imgUrl);
        }

        hotel.setUpdatedAt(LocalDateTime.now());

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

    @Override
    public HotelDTO updatePlan(Long id, Integer planId) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found"));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan Not Found"));
        hotel.setUpdatedAt(LocalDateTime.now());
        hotel.setPlan(plan);
        return hotelRepository.save(hotel).toDTO();
    }

}
