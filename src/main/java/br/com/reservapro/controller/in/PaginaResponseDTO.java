package br.com.reservapro.controller.in;

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
