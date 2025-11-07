package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.HotelMapper;
import com.quodex.dineboard.dto.response.HotelResponse;
import com.quodex.dineboard.dto.request.HotelRequest;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.model.User;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.PlanRepository;
import com.quodex.dineboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final FileUploadService fileUploadService;

    //  Create new hotel
    @Override
    public HotelResponse createHotel(HotelRequest request, String ownerId, MultipartFile file) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plan plan = null;
        if (request.getPlanId() != null) {
            plan = planRepository.findById(request.getPlanId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
        }

        // Upload logo image (optional)
        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            request.setLogoUrl(imgUrl);
        }

        // Map and set timestamps
        Hotel hotel = HotelMapper.toEntity(request, owner, plan);
        hotel.setCreatedAt(LocalDateTime.now());
        hotel.setUpdatedAt(LocalDateTime.now());

        hotel = hotelRepository.save(hotel);
        return HotelMapper.toResponse(hotel);
    }

    //  Get hotel by ID
    @Override
    public HotelResponse getHotelById(String id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));
        return HotelMapper.toResponse(hotel);
    }

    //  Get all hotels
    @Override
    public List<HotelResponse> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper::toResponse)
                .toList();
    }

    //  Update existing hotel
    @Override
    public HotelResponse updateHotel(String id, HotelRequest request, MultipartFile file) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Plan plan = null;
        if (request.getPlanId() != null) {
            plan = planRepository.findById(request.getPlanId())
                    .orElseThrow(() -> new RuntimeException("Plan not found"));
        }

        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        hotel.setContactEmail(request.getContactEmail());
        hotel.setContactPhone(request.getContactPhone());
        hotel.setWebsite(request.getWebsite());
        hotel.setDescription(request.getDescription());
        hotel.setOwner(owner);
        hotel.setPlan(plan);

        // Upload and update image (optional)
        if (file != null && !file.isEmpty()) {
            String imgUrl = fileUploadService.uploadFile(file);
            hotel.setLogoUrl(imgUrl);
        } else if (request.getLogoUrl() != null) {
            hotel.setLogoUrl(request.getLogoUrl());
        }

        hotel.setUpdatedAt(LocalDateTime.now());
        hotel = hotelRepository.save(hotel);
        return HotelMapper.toResponse(hotel);
    }

    //  Delete hotel
    @Override
    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }

    //  Get hotel by user
    @Override
    public HotelResponse getHotelByUserId(String userId) {
        Hotel hotel = hotelRepository.findByOwnerId(userId)
                .orElseThrow(() -> new RuntimeException("Hotel not found for user ID: " + userId));
        return HotelMapper.toResponse(hotel);
    }

    //  Update plan
    @Override
    public HotelResponse updatePlan(String id, String planId) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        hotel.setPlan(plan);
        hotel.setUpdatedAt(LocalDateTime.now());
        hotel = hotelRepository.save(hotel);
        return HotelMapper.toResponse(hotel);
    }
}
