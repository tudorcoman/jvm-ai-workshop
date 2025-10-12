package com.workshop.ai.repository

import com.workshop.ai.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for Document entities.
 * Provides CRUD operations and can be extended with custom queries.
 */
@Repository
interface DocumentRepository : JpaRepository<Document, Long> {
    
    /**
     * Find documents by title containing the given text (case-insensitive).
     */
    fun findByTitleContainingIgnoreCase(title: String): List<Document>
    
    /**
     * Find documents by exact title match.
     */
    fun findByTitle(title: String): List<Document>
}
