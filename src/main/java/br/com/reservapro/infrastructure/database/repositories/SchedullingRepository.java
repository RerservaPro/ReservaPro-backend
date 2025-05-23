package br.com.reservapro.infrastructure.database.repositories;

import br.com.reservapro.infrastructure.database.entities.schedule.SchedullingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedullingRepositoryImpl extends JpaRepository<SchedullingEntity, String> {
}
