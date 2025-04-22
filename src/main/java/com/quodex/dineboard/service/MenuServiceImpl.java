package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.MenuDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Hotel hotel = hotelRepository.findById(menuDTO.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        Menu menu = new Menu();
        menu.setTitle(menuDTO.getTitle());
        menu.setHotel(hotel);
        menu.setIsPublished(false);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());

        return menuRepository.save(menu).toDTO();
    }

    @Override
    public MenuDTO getMenuById(Long id) {
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
    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setTitle(menuDTO.getTitle());
        menu.setIsPublished(menuDTO.getIsPublished());
        menu.setUpdatedAt(LocalDateTime.now());

        return menuRepository.save(menu).toDTO();
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
