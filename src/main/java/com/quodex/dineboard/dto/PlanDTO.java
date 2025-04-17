package com.quodex.dineboard.dto;

import com.quodex.dineboard.model.Plan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlanDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean allowsWhiteLabeling;

    public Plan toEntity() {
        return Plan.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .allowsWhiteLabeling(this.allowsWhiteLabeling)
                .build();
    }
}
