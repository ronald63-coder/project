package com.construction.construction_permit_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "🚀 Construction Permit System is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "✅ Application is healthy!";
    }
}
