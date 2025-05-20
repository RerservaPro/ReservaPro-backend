package br.com.reservapro.infrastructure.database.repositories;

import br.com.reservapro.infrastructure.database.entities.servico.ServicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<ServicoEntity, String> {
    Page<ServicoEntity> findByEstaAtivoAndNomeContainingIgnoreCase(Boolean estaAtivo, String nome, Pageable pageable);
}
