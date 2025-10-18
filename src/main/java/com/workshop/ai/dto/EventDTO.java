package com.workshop.ai.dto;

import org.jetbrains.annotations.NotNull;

public record EventDTO(String id, @NotNull String userId, @NotNull String productId, @NotNull String eventType) { }
