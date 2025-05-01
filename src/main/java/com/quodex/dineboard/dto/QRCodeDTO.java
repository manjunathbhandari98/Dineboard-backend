package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Hotel;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;

public class QRCodeDTO {

    private Long id;
    private String label;
    private String menuId;
    private Long hotelId;
    private String url;
    private byte[] urlBytes;

    public QRCodeDTO() {}

    public QRCodeDTO(Long id, String label, String menuId, Long hotelId, String url, byte[] urlBytes) {
        this.id = id;
        this.label = label;
        this.menuId = menuId;
        this.hotelId = hotelId;
        this.url = url;
        this.urlBytes = urlBytes;
    }

    public QRCode toEntity(Menu menu, Hotel hotel) {
        QRCode qrCode = new QRCode();
        qrCode.setId(this.id);
        qrCode.setHotel(hotel);
        qrCode.setLabel(this.label);
        qrCode.setMenu(menu);
        qrCode.setUrl(this.url);
        qrCode.setUrlBytes(this.urlBytes);
        return qrCode;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getUrlBytes() {
        return urlBytes;
    }

    public void setUrlBytes(byte[] urlBytes) {
        this.urlBytes = urlBytes;
    }
}
