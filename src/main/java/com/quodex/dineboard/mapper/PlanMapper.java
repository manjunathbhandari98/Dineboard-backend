package com.quodex.dineboard.mapper;

import com.quodex.dineboard.dto.request.PlanRequest;
import com.quodex.dineboard.dto.response.PlanResponse;
import com.quodex.dineboard.model.Plan;

public class PlanMapper {
    public static Plan toEntity(PlanRequest request){
        return Plan.builder()
                .name(request.getName())
                .description(request.getDescription())
                .allowedMenus(request.getAllowedMenus())
                .allowsWhiteLabeling(request.isAllowsWhiteLabeling())
                .features(request.getFeatures())
                .price(request.getPrice())
                .highlighted(request.isHighlighted())
                .build();
    }

    public static PlanResponse toResponse(Plan plan){
        return PlanResponse.builder()
                .planId(plan.getId())
                .allowedMenus(plan.getAllowedMenus())
                .allowsWhiteLabeling(plan.isAllowsWhiteLabeling())
                .description(plan.getDescription())
                .features(plan.getFeatures())
                .highlighted(plan.isHighlighted())
                .price(plan.getPrice())
                .name(plan.getName())
                .build();
    }
}
