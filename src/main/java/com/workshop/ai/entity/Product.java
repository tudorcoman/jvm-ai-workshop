package com.workshop.ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 384) // TODO: adjust this for using a different embedding model
    private float[] embedding;

    // Constructors, getters, and setters

    public Product() {
        this.id = UUID.randomUUID();
    }

    public Product(String name, String description, float[] embedding) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.embedding = Arrays.copyOf(embedding, embedding.length);
    }

    public Product(String id, String name, String description) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.description = description;
        this.embedding = Arrays.copyOf(embedding, embedding.length);
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
