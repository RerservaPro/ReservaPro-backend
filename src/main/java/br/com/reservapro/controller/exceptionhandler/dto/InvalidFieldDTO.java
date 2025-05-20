package br.com.reservapro.controller.exceptionhandler.dto;

import lombok.Builder;

@Builder
public record InvalidFieldDTO(
        String name,
        String description,
        String value
) {

}
