package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRCodeRepository extends JpaRepository<QRCode, String> {
    List<QRCode> findByMenuId(String menuId);

    @Transactional
    void deleteByMenu(Menu menu);
}
