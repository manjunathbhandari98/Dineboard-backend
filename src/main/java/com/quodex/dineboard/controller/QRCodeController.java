package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.request.QRCodeRequest;
import com.quodex.dineboard.dto.response.QRCodeResponse;
import com.quodex.dineboard.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qrcodes")
@CrossOrigin
@RequiredArgsConstructor
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<List<QRCodeResponse>> getAllQRCodes() {
        List<QRCodeResponse> qrCodes = qrCodeService.getAllQRCodes();
        return ResponseEntity.ok(qrCodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QRCodeResponse> getQRCodeById(@PathVariable String id) {
        QRCodeResponse qrCode = qrCodeService.getQRCodeById(id);
        return ResponseEntity.ok(qrCode);
    }

    @PostMapping
    public ResponseEntity<QRCodeResponse> createQRCode(@RequestBody QRCodeRequest request) {
        QRCodeResponse createdQRCode = qrCodeService.createQRCode(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQRCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QRCodeResponse> updateQRCode(@PathVariable String id, @RequestBody QRCodeRequest request) {
        QRCodeResponse updatedQRCode = qrCodeService.updateQRCode(id, request);
        return ResponseEntity.ok(updatedQRCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQRCode(@PathVariable String id) {
        qrCodeService.deleteQRCode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<List<QRCodeResponse>> getQRCodesByMenuId(@PathVariable String menuId) {
        List<QRCodeResponse> qrCodes = qrCodeService.getQRCodesByMenuId(menuId);
        return ResponseEntity.ok(qrCodes);
    }
}
