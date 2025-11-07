package com.quodex.dineboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qrcodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String label;

    @ManyToOne
    private Menu menu;

    @ManyToOne
    private Hotel hotel;

    @Lob
    private String url;

    @Lob
    private byte[] urlBytes;

}
