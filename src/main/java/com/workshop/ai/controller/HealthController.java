package com.workshop.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Health check REST controller to verify the application is running.
 * This demonstrates Java 21 features and Spring Boot integration.
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "UP",
            "message", "JVM AI Workshop is running",
            "language", "Java 21"
        );
    }
}
