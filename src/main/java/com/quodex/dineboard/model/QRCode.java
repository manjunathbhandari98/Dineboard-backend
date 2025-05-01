package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.QRCodeDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "qrcodes")
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToOne
    private Menu menu;

    @ManyToOne
    private Hotel hotel;

    @Lob
    private String url;

    @Lob
    private byte[] urlBytes;

    public QRCode() {}

    public QRCode(Long id, String label, Menu menu, Hotel hotel, String url, byte[] urlBytes) {
        this.id = id;
        this.label = label;
        this.menu = menu;
        this.hotel = hotel;
        this.url = url;
        this.urlBytes = urlBytes;
    }

    public QRCodeDTO toDTO() {
        String base64Image = this.urlBytes != null ? "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(this.urlBytes) : null;
        return new QRCodeDTO(
                this.id,
                this.label,
                this.menu != null ? this.menu.getId() : null,
                this.hotel != null? this.hotel.getId() :null,
                base64Image,
                this.urlBytes
        );
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
