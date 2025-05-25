package br.com.reservapro.infrastructure.controller.costumer.dto;
import jakarta.validation.constraints.*;

public record CustomerDto(
        @Email
        @NotNull
        String email,

        @NotNull
        String phoneNumber
) {
}
