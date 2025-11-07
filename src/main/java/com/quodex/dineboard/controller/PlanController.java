package com.quodex.dineboard.controller;

import com.quodex.dineboard.dto.request.PlanRequest;
import com.quodex.dineboard.dto.response.PlanResponse;
import com.quodex.dineboard.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        List<PlanResponse> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponse> getPlanById(@PathVariable String id) {
        PlanResponse plan = planService.getPlanById(id);
        return ResponseEntity.ok(plan);
    }

    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(@RequestBody PlanRequest request) {
        PlanResponse createdPlan = planService.createPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> updatePlan(@PathVariable String id, @RequestBody PlanRequest request) {
        PlanResponse updatedPlan = planService.updatePlan(id, request);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable String id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
