package com.workshop.ai.repository;

import com.workshop.ai.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(
            value = """
            SELECT p.*
            FROM products p, users u
            WHERE u.id = :userId
            AND p.id NOT IN (
                SELECT product_id FROM events WHERE user_id = :userId
            )
            ORDER BY cast(:userEmbedding as vector) <=> p.embedding ASC
        """,
        nativeQuery = true
    )
    List<Product> findRecommendedProducts(@Param("userId") UUID userId, @Param("userEmbedding") float[] userEmbedding);
}
