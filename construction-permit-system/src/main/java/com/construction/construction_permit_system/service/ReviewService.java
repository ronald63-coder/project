package com.construction.construction_permit_system.service;

import com.construction.construction_permit_system.model.Buildingplan;
import com.construction.construction_permit_system.model.Review;
import com.construction.construction_permit_system.model.User;
import com.construction.construction_permit_system.repository.BuildingPlanRepository;
import com.construction.construction_permit_system.repository.ReviewRepository;
import com.construction.construction_permit_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BuildingPlanRepository buildingPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(Review review, Long buildingPlanId, Long reviewerId) {
        Optional<Buildingplan> buildingPlan = buildingPlanRepository.findById(buildingPlanId);
        Optional<User> reviewer = userRepository.findById(reviewerId);

        if (buildingPlan.isPresent() && reviewer.isPresent()) {
            review.setBuildingPlan(buildingPlan.get());
            review.setReviewer(reviewer.get());
            return reviewRepository.save(review);
        }
        return null;
    }

    public List<Review> getReviewsByBuildingPlan(Long buildingPlanId) {
        return reviewRepository.findByBuildingPlanId(buildingPlanId);
    }

    public List<Review> getReviewsByReviewer(Long reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId);
    }
}
