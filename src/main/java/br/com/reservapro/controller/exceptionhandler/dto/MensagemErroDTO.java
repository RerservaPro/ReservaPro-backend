package br.com.reservapro.controller.exceptionhandler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record MensagemErroDTO(
        String codigo,
        int status,
        String mensagem,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT-3")
        Instant momento,
        String caminho,
        List<CampoInvalidodDTO> camposInvalidos
) {
}
