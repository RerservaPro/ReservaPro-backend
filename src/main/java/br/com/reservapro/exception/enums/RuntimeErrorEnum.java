package br.com.reservapro.exception.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RuntimeErrorEnum {
    ERR0001("INVALID_ARGUMENTS", "Existem campos inválidos na solicitação."),
    ERR0002("DATA_INTEGRITY_VIOLATION", "Houve um erro nesta operação com o banco de dados."),
    ERR0003("RESOURCE_NOT_FOUND", "O serviço solicitado não foi encontrado.");

    private final String error;
    private final String description;
}
