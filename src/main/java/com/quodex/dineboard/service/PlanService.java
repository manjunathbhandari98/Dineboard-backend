package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    List<PlanDTO> getAllPlans();
    PlanDTO getPlanById(Long id);
    PlanDTO createPlan(PlanDTO planDTO);
    PlanDTO updatePlan(Long id, PlanDTO planDTO);
    void deletePlan(Long id);
}
