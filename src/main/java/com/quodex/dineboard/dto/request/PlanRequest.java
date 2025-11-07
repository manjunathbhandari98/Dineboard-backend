package com.quodex.dineboard.dto.request;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanRequest {

    private String name;
    private String description;
    private double price;
    private boolean allowsWhiteLabeling;
    private boolean highlighted;
    private Integer allowedMenus;
    private List<String> features;
}
