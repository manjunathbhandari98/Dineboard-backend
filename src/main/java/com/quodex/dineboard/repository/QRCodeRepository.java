package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}
