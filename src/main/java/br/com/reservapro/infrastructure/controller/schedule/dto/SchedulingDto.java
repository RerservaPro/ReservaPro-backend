package br.com.reservapro.infrastructure.controller.schedule.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SchedulingDto(
        String id,
        @NotNull(groups = {OnCreate.class}, message = "Schedule date is required")
        Date schedulingDate
) {
    public interface OnCreate {
    }
}
