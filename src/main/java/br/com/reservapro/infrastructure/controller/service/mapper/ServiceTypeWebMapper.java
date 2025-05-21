package br.com.reservapro.infrastructure.controller.service.mapper;

import br.com.reservapro.domain.ServiceType;
import br.com.reservapro.domain.pagination.PageResponse;
import br.com.reservapro.domain.pagination.PageResponseDTO;
import br.com.reservapro.infrastructure.controller.service.dto.ServiceTypeDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceTypeWebMapper {
    ServiceType mapToDomain(ServiceTypeDTO serviceTypeDTO);
    ServiceTypeDTO mapToDTO(ServiceType serviceType);

    PageResponseDTO<ServiceTypeDTO> mapToPageResponseDTO(PageResponse<ServiceType> pageResponse);
}




