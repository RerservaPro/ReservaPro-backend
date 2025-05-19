package br.com.reservapro.infrastructure.controller.servico.mapper;

import br.com.reservapro.domain.pagination.PaginaResponse;
import br.com.reservapro.domain.Servico;
import br.com.reservapro.domain.pagination.PaginaResponseDTO;
import br.com.reservapro.infrastructure.controller.servico.dto.ServicoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoWebMapper {
    ServicoDTO mapToDTO(Servico servico);
    Servico mapToDomain(ServicoDTO servicoDTO);
    PaginaResponseDTO<ServicoDTO> mapToPaginaResponseDTO(PaginaResponse<Servico> paginaResponse);
}
