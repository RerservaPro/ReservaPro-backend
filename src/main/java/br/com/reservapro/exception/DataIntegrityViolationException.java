package br.com.reservapro.exception;

import br.com.reservapro.exception.enums.RuntimeErrorEnum;
import lombok.Getter;

@Getter
public class DataIntegrityViolationException extends RuntimeException {
    private final RuntimeErrorEnum error;

    public DataIntegrityViolationException(RuntimeErrorEnum error) {
        super(error.getDescription());
        this.error = error;
    }
}
