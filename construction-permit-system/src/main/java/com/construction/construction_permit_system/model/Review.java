package com.construction.construction_permit_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000) // Allow longer comments
    private String comments;

    @Column(nullable = false)
    private String decision; // "APPROVE", "REJECT", "REQUEST_CHANGES"

    private LocalDateTime reviewDate;

    private String reviewStage; // "ENGINEER_REVIEW", "COMMITTEE_REVIEW"

    // Who wrote this review?
    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    // Which building plan is this review for?
    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne
    @JoinColumn(name = "building_plan_id", nullable = false)
    private Buildingplan buildingPlan;

    // === CONSTRUCTORS ===


    public Review(String comments, String decision, String reviewStage) {
        this.comments = comments;
        this.decision = decision;
        this.reviewStage = reviewStage;
        this.reviewDate = LocalDateTime.now();
    }

    public Review() {

    }

    // === GETTERS AND SETTERS ===
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewStage() {
        return reviewStage;
    }

    public void setReviewStage(String reviewStage) {
        this.reviewStage = reviewStage;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Buildingplan getBuildingPlan() {
        return buildingPlan;
    }

    public void setBuildingPlan(Buildingplan buildingPlan) {
        this.buildingPlan = buildingPlan;
    }
}
