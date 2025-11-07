package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.QRCodeRequest;
import com.quodex.dineboard.dto.response.QRCodeResponse;
import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;

public class QRCodeMapper {
    public static QRCode toEntity(QRCodeRequest request, Menu menu, Hotel hotel){
        return QRCode.builder()
                .label(request.getLabel())
                .menu(menu)
                .hotel(hotel)
                .url(request.getUrl())
                .build();
    }

    public static QRCodeResponse toResponse(QRCode qrCode){
        return QRCodeResponse.builder()
                .id(qrCode.getId())
                .label(qrCode.getLabel())
                .url(qrCode.getUrl())
                .urlBytes(qrCode.getUrlBytes())
                .menu(qrCode.getMenu() != null
                        ? MenuMapper.toResponse(qrCode.getMenu())
                        : null)
                .hotel(qrCode.getHotel() != null
                        ? HotelMapper.toResponse(qrCode.getHotel())
                        : null)
                .build();
    }
}
