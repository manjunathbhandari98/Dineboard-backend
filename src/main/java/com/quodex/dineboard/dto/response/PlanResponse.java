package com.quodex.dineboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanResponse {
    private String planId;
    private String name;
    private String description;
    private double price;
    private boolean allowsWhiteLabeling;
    private boolean highlighted;
    private Integer allowedMenus;
    private List<String> features;
}
