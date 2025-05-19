package br.com.reservapro.infrastructure.database.repositories;

import br.com.reservapro.infrastructure.database.entities.servico.ServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicoRepositoryImpl extends JpaRepository<ServicoEntity, String> {
    @Query(value = "SELECT * FROM tb_servicos WHERE esta_ativo = ?1 AND nome ILIKE CONCAT('%', ?2, '%')", nativeQuery = true)
    Page<ServicoEntity> findByPagination(boolean isActive, Pageable pageable, String name);
}
