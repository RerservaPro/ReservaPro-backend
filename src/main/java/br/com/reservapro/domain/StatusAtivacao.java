package br.com.reservapro.domain;

import lombok.*;

import java.time.Instant;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusAtivacao {
    private Instant dataCriacao;
    private Instant dataDesativacao;
    private boolean estaAtivo;
}
