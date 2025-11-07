package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.PlanMapper;
import com.quodex.dineboard.dto.request.PlanRequest;
import com.quodex.dineboard.dto.response.PlanResponse;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public List<PlanResponse> getAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(PlanMapper::toResponse)
                .toList();
    }

    @Override
    public PlanResponse getPlanById(String id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
        return PlanMapper.toResponse(plan);
    }

    @Override
    public PlanResponse createPlan(PlanRequest dto) {
        Plan plan = PlanMapper.toEntity(dto);
        return PlanMapper.toResponse(planRepository.save(plan));
    }

    @Override
    public PlanResponse updatePlan(String id, PlanRequest dto) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));

        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        plan.setPrice(dto.getPrice());
        plan.setAllowsWhiteLabeling(dto.isAllowsWhiteLabeling());
        plan.setAllowedMenus(dto.getAllowedMenus());
        plan.setHighlighted(dto.isHighlighted());
        plan.setFeatures(dto.getFeatures());

        plan = planRepository.save(plan);
        return PlanMapper.toResponse(plan);
    }

    @Override
    public void deletePlan(String id) {
        if (!planRepository.existsById(id)) {
            throw new RuntimeException("Plan not found with id: " + id);
        }
        planRepository.deleteById(id);
    }
}
