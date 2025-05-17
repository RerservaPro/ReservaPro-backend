package br.com.reservapro.controller.in.servico.mapper;

import br.com.reservapro.domain.PaginaResponse;
import br.com.reservapro.domain.servico.Servico;
import br.com.reservapro.controller.in.PaginaResponseDTO;
import br.com.reservapro.controller.in.servico.dto.ServicoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoWebMapper {
    ServicoDTO mapToDTO(Servico servico);
    Servico mapToDomain(ServicoDTO servicoDTO);
    PaginaResponseDTO<ServicoDTO> mapToPaginaResponseDTO(PaginaResponse<Servico> paginaResponse);
}
