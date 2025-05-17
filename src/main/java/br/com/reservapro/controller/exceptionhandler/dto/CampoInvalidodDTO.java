package br.com.reservapro.controller.exceptionhandler.dto;

import lombok.Builder;

@Builder
public record CampoInvalidodDTO(
        String nome,
        String descricao,
        String valor
) {

}
