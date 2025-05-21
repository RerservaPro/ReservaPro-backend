package br.com.reservapro.infrastructure.database.mappers;

import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.database.entities.schedule.SchedullingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulePersistenceMapper {
    SchedullingEntity mapToEntity(Schedulling schedulling);
    Schedulling mapToDomain(SchedullingEntity schedullingEntity);
}
