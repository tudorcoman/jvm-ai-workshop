package com.workshop.ai.dto;

import org.jetbrains.annotations.NotNull;

public record ProductDTO(String id, @NotNull String name, @NotNull String description) { }
