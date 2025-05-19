package br.com.reservapro.exceptions;

import br.com.reservapro.exceptions.enums.RuntimeErroEnum;
import lombok.Getter;

@Getter
public class RecursoNaoEncontradoException extends RuntimeException {
    private final RuntimeErroEnum erro;

    public RecursoNaoEncontradoException(RuntimeErroEnum erro) {
        super(erro.getDescricao());
        this.erro = erro;
    }
}
