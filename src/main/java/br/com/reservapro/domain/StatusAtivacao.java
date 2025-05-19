package br.com.reservapro.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusAtivacao {
    private Instant dataCriacao;
    private Instant dataDesativacao;
    private boolean estaAtivo;
}
