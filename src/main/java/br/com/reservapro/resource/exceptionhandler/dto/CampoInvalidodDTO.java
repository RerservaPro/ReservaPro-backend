package br.com.reservapro.resource.exceptionhandler.dto;

import lombok.Builder;

@Builder
public record CampoInvalidodDTO(
        String nome,
        String descricao,
        String valor
) {

}
