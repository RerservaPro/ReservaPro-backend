package br.com.reservapro.domain.pagination;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginaResponse<T> {
    private Integer tamanhoPagina;
    private Long totalElemento;
    private Integer totalPagina;
    private Integer paginaAtual;
    private Integer proximaPagina;
    private Integer paginaAnteiror;
    private Set<T> conteudo;
}
