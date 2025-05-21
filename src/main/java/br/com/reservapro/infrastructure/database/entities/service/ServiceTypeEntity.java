package br.com.reservapro.infrastructure.database.entities.service;

import br.com.reservapro.infrastructure.database.entities.ActivateStatusEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_servicos")
public class ServiceTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    @Column(name = "nome", nullable = false)
    private String name;
    @Column(name = "preco",nullable = false)
    private BigDecimal price;
    @Column(name = "descricao",nullable = false)
    private String description;
    @Embedded
    private ActivateStatusEntity activateStatus;
}
