package com.construction.construction_permit_system;

// Important: Same package as main class

import com.construction.construction_permit_system.model.PermitStatus;
import com.construction.construction_permit_system.model.User;
import com.construction.construction_permit_system.model.Buildingplan;
import com.construction.construction_permit_system.repository.UserRepository;
import com.construction.construction_permit_system.repository.BuildingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  // This annotation makes Spring find and run this class
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingPlanRepository buildingPlanRepository;

    @Override
    public void run(String... args) {
        // Only run if database is empty
        if (userRepository.count() == 0) {
            System.out.println("🔄 Initializing test data...");

            // Create Admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@construction.com");
            admin.setPassword("admin123");
            admin.setFullName("System Administrator");
            admin.setRole("ADMIN");
            userRepository.save(admin);

            // Create Applicant user
            User applicant = new User();
            applicant.setUsername("john_doe");
            applicant.setEmail("john@example.com");
            applicant.setPassword("password123");
            applicant.setFullName("John Doe");
            applicant.setRole("APPLICANT");
            applicant.setPhoneNumber("123-456-7890");
            User savedApplicant = userRepository.save(applicant);

            // Create Engineer user
            User engineer = new User();
            engineer.setUsername("engineer_smith");
            engineer.setEmail("smith@county.gov");
            engineer.setPassword("engineer123");
            engineer.setFullName("Jane Smith");
            engineer.setRole("ENGINEER");
            userRepository.save(engineer);

            // Create Committee user
            User committee = new User();
            committee.setUsername("committee_jones");
            committee.setEmail("jones@county.gov");
            committee.setPassword("committee123");
            committee.setFullName("Bob Jones");
            committee.setRole("COMMITTEE");
            userRepository.save(committee);

            // Create a test building plan
            Buildingplan plan = new Buildingplan();
            plan.setProjectName("Residential House Construction");
            plan.setLocation("123 Main Street, Springfield");
            plan.setDescription("Two-story residential building with 3 bedrooms and 2 bathrooms");
            plan.setApplicant(savedApplicant);
            plan.setStatus(PermitStatus.SUBMITTED);
            plan.setSubmissionDate(java.time.LocalDate.now());
            buildingPlanRepository.save(plan);

            System.out.println("✅ Test data initialized successfully!");
            System.out.println("   - Created 4 users (admin, applicant, engineer, committee)");
            System.out.println("   - Created 1 building plan");
            System.out.println("   👉 You can now test the API endpoints");
        } else {
            System.out.println("📊 Database already has data. Skipping initialization.");
        }
    }
}