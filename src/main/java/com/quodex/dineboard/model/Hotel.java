package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.HotelDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Entity
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String logoUrl;
    private String address;
    private String contactEmail;
    private String contactPhone;

    @OneToOne
    private User owner;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Hotel.java (Entity)
    public HotelDTO toDTO() {
        return HotelDTO.builder()
                .id(this.id)
                .name(this.name)
                .logoUrl(this.logoUrl)
                .address(this.address)
                .contactEmail(this.contactEmail)
                .contactPhone(this.contactPhone)
                .ownerId(this.owner != null ? this.owner.getId() : null)
                .build();
    }

}
