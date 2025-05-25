package br.com.reservapro.infrastructure.database.entities.servico;

import br.com.reservapro.infrastructure.database.entities.StatusAtivacaoEntity;
import br.com.reservapro.infrastructure.database.entities.costumer.CustomerEntity;
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
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal preco;
    @Column(nullable = false)
    private String descricao;
    @Embedded
    private StatusAtivacaoEntity statusAtivacao;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private CustomerEntity customer;
}
