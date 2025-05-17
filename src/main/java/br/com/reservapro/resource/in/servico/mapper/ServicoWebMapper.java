package br.com.reservapro.resource.in.servico.mapper;

import br.com.reservapro.domain.PaginaResponse;
import br.com.reservapro.domain.servico.Servico;
import br.com.reservapro.resource.in.PaginaResponseDTO;
import br.com.reservapro.resource.in.servico.dto.ServicoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicoWebMapper {
    ServicoDTO mapToDTO(Servico servico);
    Servico mapToDomain(ServicoDTO servicoDTO);
    PaginaResponseDTO<ServicoDTO> mapToPaginaResponseDTO(PaginaResponse<Servico> paginaResponse);
}
