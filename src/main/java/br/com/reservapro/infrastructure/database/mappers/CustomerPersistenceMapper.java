package br.com.reservapro.infrastructure.database.mappers;

import br.com.reservapro.domain.Customer;
import br.com.reservapro.infrastructure.database.entities.costumer.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {
    CustomerEntity mapToEntity(Customer customer);
    Customer mapToDomain(CustomerEntity customerEntity);
}
