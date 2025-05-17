package br.com.reservapro.infrastructure.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StatusAtivacaoEntity {
    @Column(name = "data_criacao",nullable = false)
    private Instant dataCriacao;
    @Column(name = "data_desativacao")
    private Instant dataDesativacao;
    @Column(name = "esta_ativo", nullable = false)
    private boolean estaAtivo;
}
