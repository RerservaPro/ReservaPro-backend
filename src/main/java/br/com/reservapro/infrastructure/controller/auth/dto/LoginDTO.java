package br.com.reservapro.infrastructure.controller.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "email is mandatory")
        String email,
        @NotBlank(message = "password is mandatory")
        String password) {
}
