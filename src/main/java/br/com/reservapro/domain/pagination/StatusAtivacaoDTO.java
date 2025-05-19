package br.com.reservapro.domain.pagination;

import java.time.Instant;

public record StatusAtivacaoDTO(
        Instant dataCriacao,
        Instant dataDesativacao,
        boolean estaAtivo
) {

}
