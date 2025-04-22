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

    private String url;

    public QRCode() {}

    public QRCode(Long id, String label, Menu menu, String url) {
        this.id = id;
        this.label = label;
        this.menu = menu;
        this.url = url;
    }

    public QRCodeDTO toDTO() {
        return new QRCodeDTO(
                this.id,
                this.label,
                this.menu != null ? this.menu.getId() : null,
                this.url
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
