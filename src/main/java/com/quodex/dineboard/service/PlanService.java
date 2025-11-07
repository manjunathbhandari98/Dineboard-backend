package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.request.PlanRequest;
import com.quodex.dineboard.dto.response.PlanResponse;

import java.util.List;

public interface PlanService {
    List<PlanResponse> getAllPlans();
    PlanResponse getPlanById(String id);
    PlanResponse createPlan(PlanRequest planRequest);
    PlanResponse updatePlan(String id, PlanRequest planRequest);
    void deletePlan(String id);
}
