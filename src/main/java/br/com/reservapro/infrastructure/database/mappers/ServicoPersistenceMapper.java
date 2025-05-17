package br.com.reservapro.infrastructure.database.mappers;

import br.com.reservapro.domain.PaginaResponse;
import br.com.reservapro.domain.servico.Servico;
import br.com.reservapro.infrastructure.database.entities.servico.ServicoEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ServicoPersistenceMapper {
    ServicoEntity mapToEntity(Servico servico);
    Servico mapToDomain(ServicoEntity servicoEntity);

    default PaginaResponse<Servico> mapToPageResponseDomain(Page<ServicoEntity> paginaResponse) {
        int previousPage = paginaResponse.hasPrevious() ? paginaResponse.getNumber() - 1 : paginaResponse.getNumber();
        int nextPage = paginaResponse.hasNext() ? paginaResponse.getNumber() + 1 : paginaResponse.getNumber();
        Set<Servico> services = paginaResponse.getContent().stream().map(this::mapToDomain).collect(Collectors.toSet());
        return PaginaResponse.<Servico>builder()
                .tamanhoPagina(paginaResponse.getNumberOfElements())
                .totalElemento(paginaResponse.getTotalElements())
                .paginaAtual(paginaResponse.getNumber())
                .paginaAnteiror(previousPage)
                .proximaPagina(nextPage)
                .conteudo(services)
                .totalPagina(paginaResponse.getTotalPages())
                .build();
    }
}
