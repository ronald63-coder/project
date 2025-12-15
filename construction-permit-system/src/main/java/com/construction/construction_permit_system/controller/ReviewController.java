package com.construction.construction_permit_system.controller;
import com.construction.construction_permit_system.model.Review;
import com.construction.construction_permit_system.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/plan/{buildingPlanId}/reviewer/{reviewerId}")
    public Review createReview(
            @RequestBody Review review,
            @PathVariable Long buildingPlanId,
            @PathVariable Long reviewerId) {
        return reviewService.createReview(review, buildingPlanId, reviewerId);
    }

    @GetMapping("/plan/{buildingPlanId}")
    public List<Review> getReviewsByBuildingPlan(@PathVariable Long buildingPlanId) {
        return reviewService.getReviewsByBuildingPlan(buildingPlanId);
    }

    @GetMapping("/reviewer/{reviewerId}")
    public List<Review> getReviewsByReviewer(@PathVariable Long reviewerId) {
        return reviewService.getReviewsByReviewer(reviewerId);
    }
}
