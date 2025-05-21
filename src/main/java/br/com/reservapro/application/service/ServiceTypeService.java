package br.com.reservapro.application.service;

import br.com.reservapro.domain.ServiceType;
import br.com.reservapro.domain.pagination.ActivateStatus;
import br.com.reservapro.domain.pagination.PageResponse;
import br.com.reservapro.exception.DataIntegrityViolationException;
import br.com.reservapro.exception.ResourceNotFoundException;
import br.com.reservapro.exception.enums.RuntimeErrorEnum;
import br.com.reservapro.infrastructure.database.entities.service.ServiceTypeEntity;
import br.com.reservapro.infrastructure.database.mappers.ServiceTypePersistenceMapper;
import br.com.reservapro.infrastructure.database.repositories.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypePersistenceMapper mapper;

    public PageResponse<ServiceType> search(boolean isActive, int page , int pageSize, String name) {
        return mapper.mapToPageResponseDomain(
                serviceTypeRepository.findByActivateStatus_IsActiveAndNameContainingIgnoreCase(isActive, name == null ? "" : name, PageRequest.of(page, pageSize))
        );
    }

    public ServiceType findById(String id) {
        return mapper.mapToDomain(serviceTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RuntimeErrorEnum.ERR0003)
        ));
    }

    public ServiceType insert(ServiceType serviceType) {
        serviceType.setId(null);
        ActivateStatus activateStatus = ActivateStatus.builder()
                .creationDate(Instant.now())
                .isActive(true)
                .build();
        serviceType.setActivateStatus(activateStatus);
        ServiceTypeEntity saved;
        try {
            saved = serviceTypeRepository.save(mapper.mapToEntity(serviceType));
        } catch (TransactionSystemException exception) {
            throw new DataIntegrityViolationException(RuntimeErrorEnum.ERR0002);
        }
        return mapper.mapToDomain(saved);
    }

    public ServiceType update(String id, ServiceType serviceTypeNew) {
        ServiceTypeEntity serviceOld = serviceTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RuntimeErrorEnum.ERR0003)
        );
        serviceOld.setName(serviceTypeNew.getName() == null ? serviceOld.getName() : serviceTypeNew.getName());
        serviceOld.setPrice(serviceTypeNew.getPrice() == null ? serviceOld.getPrice() : serviceTypeNew.getPrice());
        serviceOld.setDescription(serviceTypeNew.getDescription() == null ? serviceOld.getDescription() : serviceTypeNew.getDescription());
        ServiceTypeEntity updated;
        try {
            updated = serviceTypeRepository.save(serviceOld);
        } catch (TransactionSystemException exception) {
            throw new DataIntegrityViolationException(RuntimeErrorEnum.ERR0002);
        }
        return mapper.mapToDomain(updated);
    }

    public void deactivate(String id) {
        ServiceTypeEntity service = serviceTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RuntimeErrorEnum.ERR0003)
        );
        service.getActivateStatus().setDeactivationDate(Instant.now());
        service.getActivateStatus().setIsActive(false);
        serviceTypeRepository.save(service);
    }

    public void delete(String id) {
        ServiceTypeEntity service = serviceTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RuntimeErrorEnum.ERR0003)
        );
        serviceTypeRepository.delete(service);
    }
}
