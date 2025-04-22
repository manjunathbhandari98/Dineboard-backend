package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.model.QRCode;

public class QRCodeDTO {

    private Long id;
    private String label;
    private Long menuId;
    private String url;

    public QRCodeDTO() {}

    public QRCodeDTO(Long id, String label, Long menuId, String url) {
        this.id = id;
        this.label = label;
        this.menuId = menuId;
        this.url = url;
    }

    public QRCode toEntity(Menu menu) {
        return new QRCode(
                this.id,
                this.label,
                menu,
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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
