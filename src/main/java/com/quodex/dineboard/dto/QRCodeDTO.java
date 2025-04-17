package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.QRCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QRCodeDTO {
    private Long id;
    private String label;
    private Long menuId;
    private String url;

    public QRCode toEntity() {
        return QRCode.builder()
                .id(this.id)
                .label(this.label)
                .url(this.url)
                .build();
    }
}
