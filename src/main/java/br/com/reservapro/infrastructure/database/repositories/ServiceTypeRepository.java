package br.com.reservapro.infrastructure.database.repositories;

import br.com.reservapro.infrastructure.database.entities.service.ServiceTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceTypeEntity, String> {
    Page<ServiceTypeEntity> findByActivateStatus_IsActiveAndNameContainingIgnoreCase(Boolean isActive, String name, Pageable pageable);
}
