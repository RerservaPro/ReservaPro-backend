package br.com.reservapro.infrastructure.controller.schedule.mapper;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.controller.schedule.dto.SchedulingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingWebMapper {
    SchedulingDto mapToDto(Schedulling schedulling);
    Schedulling mapToDomain(SchedulingDto schedulingDto);
}
