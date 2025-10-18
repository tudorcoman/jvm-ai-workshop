package com.workshop.ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @Column
    private String name;

    // Constructors, getters, and setters

    protected User() {
        this.id = UUID.randomUUID();
        this.name = "User " + this.id;
    }

    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public User(String id, String name) {
        this.id = UUID.fromString(id);
        this.name = name;
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
}
