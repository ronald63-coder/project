package com.construction.construction_permit_system.repository;

import com.construction.construction_permit_system.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBuildingPlanId(Long buildingPlanId);
    List<Review> findByReviewerId(Long reviewerId);
}
