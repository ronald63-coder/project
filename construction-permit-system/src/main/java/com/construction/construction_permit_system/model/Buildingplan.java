package com.construction.construction_permit_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Buildingplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String applicationNumber; // Will be auto-generated: "PERMIT-2024-001"

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String location;

    private String description;

    private String documentUrl; // Path to uploaded PDF/plans

    private LocalDate submissionDate;

    @Enumerated(EnumType.STRING)
    private PermitStatus status;

    // Who submitted this plan?
    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne
    @JoinColumn(name = "applicant", nullable = false)
    private User applicant;

    // All reviews for this plan
    @OneToMany(mappedBy = "buildingPlan", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // === CONSTRUCTORS ===

    public Buildingplan(String projectName, String location, String description) {
        this.projectName = projectName;
        this.location = location;
        this.description = description;
        this.submissionDate = LocalDate.now();
        this.status = PermitStatus.DRAFT; // Default status
    }

    public Buildingplan() {

    }

    // === GETTERS AND SETTERS ===
    // Generate these with Alt + Insert
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public PermitStatus getStatus() {
        return status;
    }

    public void setStatus(PermitStatus status) {
        this.status = status;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
