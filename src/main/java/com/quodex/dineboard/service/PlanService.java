package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    List<PlanDTO> getAllPlans();
    PlanDTO getPlanById(Integer id);
    PlanDTO createPlan(PlanDTO planDTO);
    PlanDTO updatePlan(Integer id, PlanDTO planDTO);
    void deletePlan(Integer id);
}
