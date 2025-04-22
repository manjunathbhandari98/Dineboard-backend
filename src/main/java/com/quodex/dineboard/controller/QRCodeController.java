package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.QRCodeDTO;
import com.quodex.dineboard.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qrcodes")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping
    public List<QRCodeDTO> getAllQRCodes() {
        return qrCodeService.getAllQRCodes();
    }

    @GetMapping("/{id}")
    public QRCodeDTO getQRCodeById(@PathVariable Long id) {
        return qrCodeService.getQRCodeById(id);
    }

    @PostMapping
    public QRCodeDTO createQRCode(@RequestBody QRCodeDTO dto) {
        return qrCodeService.createQRCode(dto);
    }

    @PutMapping("/{id}")
    public QRCodeDTO updateQRCode(@PathVariable Long id, @RequestBody QRCodeDTO dto) {
        return qrCodeService.updateQRCode(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteQRCode(@PathVariable Long id) {
        qrCodeService.deleteQRCode(id);
    }

    @GetMapping("/menu/{menuId}")
    public List<QRCodeDTO> getQRCodesByMenuId(@PathVariable Long menuId) {
        return qrCodeService.getQRCodesByMenuId(menuId);
    }
}
