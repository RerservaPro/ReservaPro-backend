package br.com.reservapro.infrastructure.controller.costumer.mapper;

import br.com.reservapro.domain.Customer;
import br.com.reservapro.infrastructure.controller.costumer.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CustomerDto mapToDto(Customer customer);
    Customer mapToDomain(CustomerDto customerDto);
}
