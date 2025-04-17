package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.MenuDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Hotel hotel;

    private boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MenuDTO toDTO() {
        return MenuDTO.builder()
                .id(this.id)
                .title(this.title)
                .hotelId(this.hotel != null ? this.hotel.getId() : null)
                .isPublished(this.isPublished)
                .build();
    }
}
