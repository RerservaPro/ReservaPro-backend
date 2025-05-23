package br.com.reservapro.infrastructure.controller.schedule.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SchedullingDto(
        Integer id,
        @NotNull(groups = {OnCreate.class}, message = "A data do agendamento é obrigatória.")
        Date schedullingDate
) {
    public interface OnCreate {
    }
}
