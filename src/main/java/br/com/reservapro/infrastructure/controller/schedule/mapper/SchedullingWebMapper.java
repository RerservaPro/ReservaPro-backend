package br.com.reservapro.infrastructure.controller.schedule.mapper;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.controller.schedule.dto.SchedullingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedullingWebMapper {
    SchedullingDto mapToDto(Schedulling schedulling);
    Schedulling mapToDomain(SchedullingDto schedullingDto);
}
