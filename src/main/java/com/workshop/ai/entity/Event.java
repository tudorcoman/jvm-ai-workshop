package com.workshop.ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private UUID id;

    @Column
    @Enumerated
    private EventType eventType;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    // Constructors, getters, and setters

    protected Event() {
        this.id = UUID.randomUUID();
    }

    public Event(EventType eventType, User user, Product product) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.user = user;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
