package com.construction.construction_permit_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
 // "user" is often a reserved word in SQL

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // In real app, this should be encrypted!

    @Column(nullable = false)
    private String role; // Values: "APPLICANT", "ENGINEER", "COMMITTEE", "ADMIN"

    private String fullName;
    private String phoneNumber;

    // One user can submit many building plans
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<Buildingplan> submittedPlans = new ArrayList<>();

    // One user can write many reviews
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    // === CONSTRUCTORS ===
    public User() {
        // Default constructor required by JPA
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // === GETTERS AND SETTERS ===
    // Use IntelliJ shortcut: Alt + Insert → Getter and Setter → Select all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Buildingplan> getSubmittedPlans() {
        return submittedPlans;
    }

    public void setSubmittedPlans(List<Buildingplan> submittedPlans) {
        this.submittedPlans = submittedPlans;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
