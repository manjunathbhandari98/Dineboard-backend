package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.MenuMapper;
import com.quodex.dineboard.mapper.MenuViewMapper;
import com.quodex.dineboard.dto.request.MenuViewRequest;
import com.quodex.dineboard.dto.request.MenuRequest;
import com.quodex.dineboard.dto.response.MenuResponse;
import com.quodex.dineboard.exception.DineBoardException;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final HotelRepository hotelRepository;
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final QRCodeRepository qrCodeRepository;
    private final MenuViewRepository menuViewRepository;

    //  Create Menu
    @Override
    public MenuResponse createMenu(MenuRequest request) throws DineBoardException {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        int currentMenus = menuRepository.findByHotelId(hotel.getId()).size();
        int allowedMenus = (hotel.getPlan() != null && hotel.getPlan().getAllowedMenus() != null)
                ? hotel.getPlan().getAllowedMenus()
                : 2; // Default to 2 if no plan assigned

        if (currentMenus >= allowedMenus) {
            throw new DineBoardException("MENU_LIMIT_REACHED");
        }

        Menu menu = MenuMapper.toEntity(request, hotel);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());
        menu.setViewCount(0);
        menu.setUniqueDeviceCount(0);

        menu = menuRepository.save(menu);
        return MenuMapper.toResponse(menu);
    }

    //  Get menu by ID
    @Override
    public MenuResponse getMenuById(String id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        return MenuMapper.toResponse(menu);
    }

    //  Get all menus
    @Override
    public List<MenuResponse> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(MenuMapper::toResponse)
                .toList();
    }

    //  Get menus by hotel
    @Override
    public List<MenuResponse> getMenusByHotel(String hotelId) {
        // Verify hotel exists
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        return menuRepository.findByHotelId(hotelId)
                .stream()
                .map(MenuMapper::toResponse)
                .toList();
    }

    //  Update existing menu
    @Override
    public MenuResponse updateMenu(String id, MenuRequest request) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Update fields
        menu.setTitle(request.getTitle());
        menu.setHotel(hotel);
        menu.setPublished(request.isPublished());
        menu.setUpdatedAt(LocalDateTime.now());

        // Optional counters update
        if (request.getViewCount() != 0) {
            menu.setViewCount(request.getViewCount());
        }
        if (request.getUniqueDeviceCount() != 0) {
            menu.setUniqueDeviceCount(request.getUniqueDeviceCount());
        }

        menu = menuRepository.save(menu);
        return MenuMapper.toResponse(menu);
    }

    //  Delete menu (and cascade delete related data manually)
    @Override
    public void deleteMenu(String menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        // Delete associated entities first to prevent FK constraint issues
        menuItemRepository.deleteByMenu(menu);
        categoryRepository.deleteByMenu(menu);
        qrCodeRepository.deleteByMenu(menu);

        menuRepository.delete(menu);
    }

    //  Track menu views (with IST timezone)
    @Override
    public void trackMenuView(String menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        ZoneId indiaZone = ZoneId.of("Asia/Kolkata");
        LocalDate today = LocalDate.now(indiaZone);
        ZonedDateTime startOfDay = today.atStartOfDay(indiaZone);
        ZonedDateTime endOfDay = today.plusDays(1).atStartOfDay(indiaZone);

        // Count today's views
        long todayViewCount = menuViewRepository.countByMenuIdAndCreatedAtBetween(
                menuId,
                startOfDay.toLocalDateTime(),
                endOfDay.toLocalDateTime()
        );

        //  Save a new view record (mapper handles timestamp)
        menuViewRepository.save(MenuViewMapper.toEntity(new MenuViewRequest(), menu));

        //  Update stats
        menu.setViewCount((int) todayViewCount + 1);
        menu.setUpdatedAt(LocalDateTime.now(indiaZone));
        menuRepository.save(menu);
    }
}
