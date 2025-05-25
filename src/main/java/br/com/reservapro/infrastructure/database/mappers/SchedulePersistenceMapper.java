package br.com.reservapro.infrastructure.database.mappers;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.database.entities.schedule.SchedulingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulePersistenceMapper {
    SchedulingEntity mapToEntity(Schedulling schedulling);
    Schedulling mapToDomain(SchedulingEntity schedulingEntity);
}
