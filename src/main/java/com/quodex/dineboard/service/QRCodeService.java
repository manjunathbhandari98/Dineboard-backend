package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.QRCodeDTO;

import java.util.List;

public interface QRCodeService {
    List<QRCodeDTO> getAllQRCodes();
    QRCodeDTO getQRCodeById(Long id);
    QRCodeDTO createQRCode(QRCodeDTO qrCodeDTO);
    QRCodeDTO updateQRCode(Long id, QRCodeDTO qrCodeDTO);
    void deleteQRCode(Long id);
    List<QRCodeDTO> getQRCodesByMenuId(String menuId);
}
