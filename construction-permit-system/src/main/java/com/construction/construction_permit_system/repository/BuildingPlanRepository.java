package com.construction.construction_permit_system.repository;

import com.construction.construction_permit_system.model.Buildingplan;
import com.construction.construction_permit_system.model.PermitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildingPlanRepository extends JpaRepository<Buildingplan, Long> {
    List<Buildingplan> findByApplicantId(Long applicantId);
    List<Buildingplan> findByStatus(PermitStatus status);
}