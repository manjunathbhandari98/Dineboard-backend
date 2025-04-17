package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.QRCodeDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "qrcodes")
public class QRCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToOne
    private Menu menu;

    private String url;

    public QRCodeDTO toDTO() {
        return QRCodeDTO.builder()
                .id(this.id)
                .label(this.label)
                .menuId(this.menu != null ? this.menu.getId() : null)
                .url(this.url)
                .build();
    }
}
