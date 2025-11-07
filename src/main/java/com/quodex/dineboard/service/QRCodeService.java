package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.QRCodeRequest;
import com.quodex.dineboard.dto.response.QRCodeResponse;

import java.util.List;

public interface QRCodeService {
    List<QRCodeResponse> getAllQRCodes();
    QRCodeResponse getQRCodeById(String id);
    QRCodeResponse createQRCode(QRCodeRequest request);
    QRCodeResponse updateQRCode(String id, QRCodeRequest request);
    void deleteQRCode(String id);
    List<QRCodeResponse> getQRCodesByMenuId(String menuId);
}
