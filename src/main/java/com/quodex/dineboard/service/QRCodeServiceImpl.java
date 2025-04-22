package com.quodex.dineboard.service.impl;

import com.quodex.dineboard.dto.QRCodeDTO;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;
import com.quodex.dineboard.repository.MenuRepository;
import com.quodex.dineboard.repository.QRCodeRepository;
import com.quodex.dineboard.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository, MenuRepository menuRepository) {
        this.qrCodeRepository = qrCodeRepository;
        this.menuRepository = menuRepository;
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
        QRCode qrCode = dto.toEntity(menu);
        return qrCodeRepository.save(qrCode).toDTO();
    }

    @Override
    public QRCodeDTO updateQRCode(Long id, QRCodeDTO dto) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QR Code not found with id: " + id));
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + dto.getMenuId()));

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
    public List<QRCodeDTO> getQRCodesByMenuId(Long menuId) {
        return qrCodeRepository.findByMenuId(menuId)
                .stream()
                .map(QRCode::toDTO)
                .collect(Collectors.toList());
    }
}
