package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.QRCodeMapper;
import com.quodex.dineboard.dto.request.QRCodeRequest;
import com.quodex.dineboard.dto.response.QRCodeResponse;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;
import com.quodex.dineboard.repository.HotelRepository;
import com.quodex.dineboard.repository.MenuRepository;
import com.quodex.dineboard.repository.QRCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final MenuRepository menuRepository;
    private final HotelRepository hotelRepository;

    private byte[] decodeBase64Image(String base64) {
        if (base64 == null || base64.isBlank()) {
            return null;
        }
        // Remove data URI prefix if present
        if (base64.contains(",")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        return Base64.getDecoder().decode(base64);
    }

    @Override
    public List<QRCodeResponse> getAllQRCodes() {
        return qrCodeRepository.findAll()
                .stream()
                .map(QRCodeMapper::toResponse)
                .toList();
    }

    @Override
    public QRCodeResponse getQRCodeById(String id) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code not found with id: " + id));
        return QRCodeMapper.toResponse(qrCode);
    }

    @Override
    public QRCodeResponse createQRCode(QRCodeRequest dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + dto.getMenuId()));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + dto.getHotelId()));

        if (dto.getUrl() != null && !dto.getUrl().isBlank()) {
            dto.setUrlBytes(decodeBase64Image(dto.getUrl()));
        }

        QRCode qrCode = QRCodeMapper.toEntity(dto, menu, hotel);
        qrCode = qrCodeRepository.save(qrCode);
        return QRCodeMapper.toResponse(qrCode);
    }

    @Override
    public QRCodeResponse updateQRCode(String id, QRCodeRequest dto) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code not found with id: " + id));

        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + dto.getMenuId()));

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + dto.getHotelId()));

        if (dto.getUrl() != null && !dto.getUrl().isBlank()) {
            dto.setUrlBytes(decodeBase64Image(dto.getUrl()));
        }

        qrCode.setLabel(dto.getLabel());
        qrCode.setMenu(menu);
        qrCode.setHotel(hotel);
        qrCode.setUrl(dto.getUrl());
        qrCode.setUrlBytes(dto.getUrlBytes());

        qrCode = qrCodeRepository.save(qrCode);
        return QRCodeMapper.toResponse(qrCode);
    }

    @Override
    public void deleteQRCode(String id) {
        if (!qrCodeRepository.existsById(id)) {
            throw new RuntimeException("QR Code not found with id: " + id);
        }
        qrCodeRepository.deleteById(id);
    }

    @Override
    public List<QRCodeResponse> getQRCodesByMenuId(String menuId) {
        return qrCodeRepository.findByMenuId(menuId)
                .stream()
                .map(QRCodeMapper::toResponse)
                .toList();
    }
}
