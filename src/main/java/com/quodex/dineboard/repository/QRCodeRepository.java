package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    List<QRCode> findByMenuId(Long menuId);
}
