package br.com.reservapro.infrastructure.database.mappers;

import br.com.reservapro.domain.ServiceType;
import br.com.reservapro.domain.pagination.PageResponse;
import br.com.reservapro.infrastructure.database.entities.service.ServiceTypeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ServiceTypePersistenceMapper {
    ServiceType mapToDomain(ServiceTypeEntity serviceTypeEntity);
    ServiceTypeEntity mapToEntity(ServiceType serviceType);

    default PageResponse<ServiceType> mapToPageResponseDomain(Page<ServiceTypeEntity> pageResponse) {
        int previousPage = pageResponse.hasPrevious() ? pageResponse.getNumber() - 1 : pageResponse.getNumber();
        int nextPage = pageResponse.hasNext() ? pageResponse.getNumber() + 1 : pageResponse.getNumber();
        Set<ServiceType> services = pageResponse.getContent().stream().map(this::mapToDomain).collect(Collectors.toSet());
        return PageResponse.<ServiceType>builder()
                .totalElement(pageResponse.getTotalElements())
                .pageSize(pageResponse.getNumberOfElements())
                .currentPage(pageResponse.getNumber())
                .previousPage(previousPage)
                .nextPage(nextPage)
                .content(services)
                .totalPage(pageResponse.getTotalPages())
                .build();
    }
}



