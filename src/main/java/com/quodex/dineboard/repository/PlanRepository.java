package com.quodex.dineboard.repository;

import com.quodex.dineboard.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
