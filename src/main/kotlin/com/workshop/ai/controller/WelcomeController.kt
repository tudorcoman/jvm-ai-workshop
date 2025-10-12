package com.workshop.ai.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Example REST controller demonstrating Kotlin integration.
 */
@RestController
@RequestMapping("/api/kotlin")
class WelcomeController {

    @GetMapping("/welcome")
    fun welcome(): Map<String, String> {
        return mapOf(
            "message" to "Welcome to JVM AI Workshop",
            "language" to "Kotlin",
            "description" to "Migrating Python AI Prototypes to Cross-Platform Solutions"
        )
    }
}
