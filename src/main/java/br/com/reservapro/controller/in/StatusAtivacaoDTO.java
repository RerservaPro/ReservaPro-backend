package br.com.reservapro.controller.in;

import java.time.Instant;

public record StatusAtivacaoDTO(
        Instant dataCriacao,
        Instant dataDesativacao,
        boolean estaAtivo
) {

}
