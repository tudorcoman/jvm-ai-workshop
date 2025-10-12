package com.workshop.ai.model

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * Example JPA entity demonstrating Hibernate integration with PostgreSQL.
 * This represents a document with vector embeddings for semantic search.
 */
@Entity
@Table(name = "documents")
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    // Note: For vector embeddings, you would typically use pgvector's vector type
    // This requires custom type mapping with Hibernate
    // Example: @Column(columnDefinition = "vector(384)")
    // val embedding: FloatArray? = null
)
