package com.construction.construction_permit_system.controller;

import com.construction.construction_permit_system.model.Buildingplan;
import com.construction.construction_permit_system.model.PermitStatus;
import com.construction.construction_permit_system.service.BuildingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/building-plans")
public class BuildingPlanController {
    @Autowired
    private BuildingPlanService buildingPlanService;

    @GetMapping
    public List<Buildingplan> getAllBuildingPlans() {
        return buildingPlanService.getAllBuildingPlans();
    }

    @PostMapping("/{applicantId}")
    public Buildingplan createBuildingPlan(
            @RequestBody Buildingplan buildingPlan,
            @PathVariable Long applicantId) {
        return buildingPlanService.createBuildingPlan(buildingPlan, applicantId);
    }

    @GetMapping("/{id}")
    public Buildingplan getBuildingPlanById(@PathVariable Long id) {
        return buildingPlanService.getBuildingPlanById(id);
    }

    @GetMapping("/applicant/{applicantId}")
    public List<Buildingplan> getBuildingPlansByApplicant(@PathVariable Long applicantId) {
        return buildingPlanService.getBuildingPlansByApplicant(applicantId);
    }

    @PatchMapping("/{id}/status")
    public Buildingplan updateStatus(
            @PathVariable Long id,
            @RequestParam PermitStatus status) {
        return buildingPlanService.updateStatus(id, status);
    }
}
