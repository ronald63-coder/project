package com.construction.construction_permit_system.model;

public enum PermitStatus { DRAFT,                     // Applicant is still filling the form
    SUBMITTED,                 // Applicant submitted for review
    UNDER_ENGINEER_REVIEW,     // With county engineer
    UNDER_COMMITTEE_REVIEW,    // With planning committee
    APPROVED,                  // Final approval
    REJECTED,                  // Final rejection
    REQUIRES_RESUBMISSION      // Need changes from applicant
}
