package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuDTO;
import com.quodex.dineboard.exception.DineBoardException;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.MenuView;
import com.quodex.dineboard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private MenuViewRepository menuViewRepository;


    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) throws DineBoardException {
        Hotel hotel = hotelRepository.findById(menuDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        int currentMenus = menuRepository.findByHotelId(hotel.getId()).size();
        int allowedMenus = hotel.getPlan() != null ? hotel.getPlan().getAllowedMenus() : 2;  // Default to 2 if no plan is assigned

        if (currentMenus >= allowedMenus) {
            throw new DineBoardException("MENU_LIMIT_REACHED");
        }

        Menu menu = new Menu();
        menu.setId(UUID.randomUUID().toString());
        menu.setTitle(menuDTO.getTitle());
        menu.setHotel(hotel);
        menu.setIsPublished(false);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());
        menu.setViewCount(0);

        return menuRepository.save(menu).toDTO();
    }


    private int getAllowedMenusForPlan(Integer planId) {
        if (planId == null) {
            return 1; // Default to BASIC if null
        }

        return switch (planId) {
            case 1 -> 1; // BASIC
            case 2 -> 5; // PRO
            case 3 -> 10; // SUPER
            default -> 1; // Default fallback
        };
    }


    @Override
    public MenuDTO getMenuById(String id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"))
                .toDTO();
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(Menu::toDTO)
                .collect(Collectors.toList());
    }

    public List<MenuDTO> getMenusByHotel(Long hotelId) {
        // Fetch the hotel to ensure it exists
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel Not Found"));

        // Fetch all menus for the hotel
        return menuRepository.findByHotelId(hotelId).stream()
                .map(Menu::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO updateMenu(String id, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setTitle(menuDTO.getTitle());
        menu.setIsPublished(menuDTO.getIsPublished());
        menu.setUpdatedAt(LocalDateTime.now());

        return menuRepository.save(menu).toDTO();
    }

    @Override
    public void deleteMenu(String menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        // Manually delete associated MenuItems
        menuItemRepository.deleteByMenu(menu); // Assuming you have a method like this in your MenuItemRepository
        categoryRepository.deleteByMenu(menu);
        qrCodeRepository.deleteByMenu(menu);
        menuRepository.delete(menu);
    }


@Override
    public void trackMenuView(String menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");

        // Get today's start and end in IST
        LocalDate today = LocalDate.now(indiaZone);
        ZonedDateTime startOfDay = today.atStartOfDay(indiaZone);
        ZonedDateTime endOfDay = today.plusDays(1).atStartOfDay(indiaZone);

        // Count views in IST range
        long todayViewCount = menuViewRepository.countByMenuIdAndCreatedAtBetween(
                menuId,
                startOfDay.toLocalDateTime(),
                endOfDay.toLocalDateTime()
        );

        // Save view with IST time
        menuViewRepository.save(new MenuView(menuId, ZonedDateTime.now(indiaZone).toLocalDateTime()));

        // Update menu stats
        menu.setViewCount((int) todayViewCount + 1);
        menu.setUpdatedAt(ZonedDateTime.now(indiaZone).toLocalDateTime());
        menuRepository.save(menu);
    }


}
