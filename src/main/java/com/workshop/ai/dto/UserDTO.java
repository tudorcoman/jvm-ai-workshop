package com.workshop.ai.dto;

import org.jetbrains.annotations.NotNull;

public record UserDTO(String id, @NotNull String name) { }
