package com.quodex.dineboard.model;

import com.quodex.dineboard.dto.PlanDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plans")
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private boolean allowsWhiteLabeling;

    public PlanDTO toDTO() {
        return PlanDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .allowsWhiteLabeling(this.allowsWhiteLabeling)
                .build();
    }
}
