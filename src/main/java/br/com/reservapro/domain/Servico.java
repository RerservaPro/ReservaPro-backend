package br.com.reservapro.domain;

import br.com.reservapro.domain.StatusAtivacao;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servico {
    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private StatusAtivacao statusAtivacao;
}
