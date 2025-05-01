package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.PlanDTO;
import com.quodex.dineboard.model.Plan;
import com.quodex.dineboard.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        return planRepository.findAll()
                .stream()
                .map(Plan::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDTO getPlanById(Integer id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
        return plan.toDTO();
    }

    @Override
    public PlanDTO createPlan(PlanDTO dto) {
        Plan plan = new Plan(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.isAllowsWhiteLabeling(),
                dto.isHighlighted(),
                dto.getAllowedMenus(),
                dto.getFeatures()
        );
        return planRepository.save(plan).toDTO();
    }

    @Override
    public PlanDTO updatePlan(Integer id, PlanDTO dto) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));

        plan.setName(dto.getName());
        plan.setDescription(dto.getDescription());
        plan.setPrice(dto.getPrice());
        plan.setAllowsWhiteLabeling(dto.isAllowsWhiteLabeling());
        plan.setAllowedMenus(dto.getAllowedMenus());
        plan.setHighlighted(dto.isHighlighted());
        plan.setFeatures(dto.getFeatures());

        return planRepository.save(plan).toDTO();
    }

    @Override
    public void deletePlan(Integer id) {
        if (!planRepository.existsById(id)) {
            throw new RuntimeException("Plan not found with id: " + id);
        }
        planRepository.deleteById(id);
    }
}
