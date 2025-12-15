package com.construction.construction_permit_system.service;
import com.construction.construction_permit_system.model.Buildingplan;
import com.construction.construction_permit_system.model.PermitStatus;
import com.construction.construction_permit_system.model.User;
import com.construction.construction_permit_system.repository.BuildingPlanRepository;
import com.construction.construction_permit_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingPlanService {
    @Autowired
    private BuildingPlanRepository buildingPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public Buildingplan createBuildingPlan(Buildingplan buildingPlan, Long applicantId) {
        Optional<User> applicant = userRepository.findById(applicantId);
        if (applicant.isPresent()) {
            buildingPlan.setApplicant(applicant.get());
            // Generate application number
            String appNumber = "PERMIT-" + java.time.Year.now().getValue() +
                    "-" + String.format("%03d", buildingPlanRepository.count() + 1);
            buildingPlan.setApplicationNumber(appNumber);
            return buildingPlanRepository.save(buildingPlan);
        }
        return null;
    }

    public List<Buildingplan> getAllBuildingPlans() {
        return buildingPlanRepository.findAll();
    }

    public Buildingplan getBuildingPlanById(Long id) {
        return buildingPlanRepository.findById(id).orElse(null);
    }

    public List<Buildingplan> getBuildingPlansByApplicant(Long applicantId) {
        return buildingPlanRepository.findByApplicantId(applicantId);
    }

    public Buildingplan updateStatus(Long id, PermitStatus status) {
        Buildingplan plan = buildingPlanRepository.findById(id).orElse(null);
        if (plan != null) {
            plan.setStatus(status);
            return buildingPlanRepository.save(plan);
        }
        return null;
    }
}
