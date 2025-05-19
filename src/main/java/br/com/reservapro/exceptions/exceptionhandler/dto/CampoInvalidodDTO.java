package br.com.reservapro.exceptions.exceptionhandler.dto;

import lombok.Builder;

@Builder
public record CampoInvalidodDTO(
        String nome,
        String descricao,
        String valor
) {

}
