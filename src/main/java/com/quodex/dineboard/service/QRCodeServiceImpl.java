package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.QRCodeDTO;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.MenuRepository;
import com.quodex.dineboard.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final MenuRepository menuRepository;
    private final HotelRepository hotelRepository;


    @Autowired
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository, MenuRepository menuRepository,
                             HotelRepository hotelRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.menuRepository = menuRepository;
        this.hotelRepository = hotelRepository;
    }

    private byte[] decodeBase64Image(String base64) {
        if (base64.contains(",")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        return Base64.getDecoder().decode(base64);
    }

    @Override
    public List<QRCodeDTO> getAllQRCodes() {
        return qrCodeRepository.findAll()
                .stream()
                .map(QRCode::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QRCodeDTO getQRCodeById(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code not found with id: " + id));
        return qrCode.toDTO();
    }

    @Override
    public QRCodeDTO createQRCode(QRCodeDTO dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + dto.getMenuId()));
        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: "+dto.getHotelId()));
        if (dto.getUrl() != null && !dto.getUrl().isEmpty()) {
            dto.setUrlBytes(decodeBase64Image(dto.getUrl()));
        }
        QRCode qrCode = dto.toEntity(menu, hotel);
        return qrCodeRepository.save(qrCode).toDTO();
    }

    @Override
    public QRCodeDTO updateQRCode(Long id, QRCodeDTO dto) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code not found with id: " + id));
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + dto.getMenuId()));

        if (dto.getUrl() != null && !dto.getUrl().isEmpty()) {
            dto.setUrlBytes(decodeBase64Image(dto.getUrl()));
        }
        qrCode.setLabel(dto.getLabel());
        qrCode.setMenu(menu);
        qrCode.setUrl(dto.getUrl());

        return qrCodeRepository.save(qrCode).toDTO();
    }

    @Override
    public void deleteQRCode(Long id) {
        if (!qrCodeRepository.existsById(id)) {
            throw new RuntimeException("QR Code not found with id: " + id);
        }
        qrCodeRepository.deleteById(id);
    }

    @Override
    public List<QRCodeDTO> getQRCodesByMenuId(String menuId) {
        return qrCodeRepository.findByMenuId(menuId)
                .stream()
                .map(QRCode::toDTO)
                .collect(Collectors.toList());
    }
}
