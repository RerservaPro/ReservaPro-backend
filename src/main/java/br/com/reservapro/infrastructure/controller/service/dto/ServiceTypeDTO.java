package br.com.reservapro.infrastructure.controller.service.dto;

import br.com.reservapro.domain.pagination.ActivateStatusDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ServiceTypeDTO(
        String id,
        @NotBlank(groups = {OnCreate.class}, message = "O nome deve ser preenchido.")
        String name,
        @NotNull(groups = {OnCreate.class}, message = "O preço não deve ser nulo.")
        @Max(groups = {OnCreate.class, OnUpdate.class}, value = 1_000_000_000, message = "O preço não pode ultrapasar 1 bilhão.")
        @PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "O preço não pode ser negativo..")
        BigDecimal price,
        @NotBlank(groups = {OnCreate.class}, message = "A descrição deve ser preenchido.")
        String description,
        ActivateStatusDTO activateStatus
) {
    public interface OnCreate {}
    public interface OnUpdate {}
}
