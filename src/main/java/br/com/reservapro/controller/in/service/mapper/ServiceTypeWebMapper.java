package br.com.reservapro.controller.in.service.mapper;

import br.com.reservapro.controller.in.PageResponseDTO;
import br.com.reservapro.controller.in.service.dto.ServiceTypeDTO;
import br.com.reservapro.domain.PageResponse;
import br.com.reservapro.domain.service.ServiceType;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceTypeWebMapper {
    ServiceType mapToDomain(ServiceTypeDTO serviceTypeDTO);
    ServiceTypeDTO mapToDTO(ServiceType serviceType);

    PageResponseDTO<ServiceTypeDTO> mapToPageResponseDTO(PageResponse<ServiceType> pageResponse);
}




