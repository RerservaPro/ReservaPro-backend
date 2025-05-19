package br.com.reservapro.domain.pagination;

import java.util.Set;

public record PaginaResponseDTO<T>(
        long tamanhoPagina,
        int totalElemento,
        int totalPagina,
        int paginaAtual,
        int proximaPagina,
        int paginaAnteiror,
        Set<T> conteudo
) {
}
