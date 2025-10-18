package com.workshop.ai.entity;

public enum EventType {
    VIEW(1),
    CLICK(2),
    PURCHASE(3);

    private final int weight;

    EventType(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
