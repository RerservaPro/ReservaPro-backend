package br.com.reservapro.exceptions.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RuntimeErroEnum {
    ERR0001("ARGUMENTOS_INVALIDOS", "Existem campos inválidos na solicitação."),
    ERR0002("VIOLACAO_DA_INTEGRIDADE_DOS_DADOS", "Houve um erro nesta operação com o banco de dados."),
    ERR0003("RECURSO_NAO_ENCONTRADO", "O serviço solicitado não foi encontrado.");

    private final String erro;
    private final String descricao;
}
