package br.com.reservapro.infrastructure.controller.email.mapper;

import br.com.reservapro.domain.Email;
import br.com.reservapro.infrastructure.controller.email.dto.EmailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailWebMapper {
    Email mapToDomain(EmailDTO emailDTO);
}
