package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.PlanDTO;
import com.quodex.dineboard.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/plans")
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<PlanDTO> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public PlanDTO getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @PostMapping
    public PlanDTO createPlan(@RequestBody PlanDTO dto) {
        return planService.createPlan(dto);
    }

    @PutMapping("/{id}")
    public PlanDTO updatePlan(@PathVariable Long id, @RequestBody PlanDTO dto) {
        return planService.updatePlan(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }
}
