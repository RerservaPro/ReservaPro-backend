package br.com.reservapro.resource.in.servico.mapper;

import br.com.reservapro.domain.PaginaResponse;
import br.com.reservapro.domain.servico.Servico;
import br.com.reservapro.resource.in.PaginaResponseDTO;
import br.com.reservapro.resource.in.servico.dto.ServicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ServicoWebMapper {
    ServicoDTO mapToDTO(Servico servico);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "nome", source = "nome"),
            @Mapping(target = "preco", source = "preco"),
            @Mapping(target = "descricao", source = "descricao"),
            @Mapping(target = "statusAtivacao", source = "statusAtivacao")
    })
    Servico mapToDomain(ServicoDTO servicoDTO);
    PaginaResponseDTO<ServicoDTO> mapToPaginaResponseDTO(PaginaResponse<Servico> paginaResponse);
}
