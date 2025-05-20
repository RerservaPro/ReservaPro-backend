package br.com.reservapro.controller.exceptionhandler;

import br.com.reservapro.exception.ResourceNotFoundException;
import br.com.reservapro.exception.DataIntegrityViolationException;
import br.com.reservapro.exception.enums.RuntimeErrorEnum;
import br.com.reservapro.controller.exceptionhandler.dto.InvalidFieldDTO;
import br.com.reservapro.controller.exceptionhandler.dto.ErrorMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerResource {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDTO> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        RuntimeErrorEnum runtimeErrorEnum = RuntimeErrorEnum.ERR0001;
        List<InvalidFieldDTO> fields = new ArrayList<>();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        exception.getBindingResult().getFieldErrors().forEach(invalidField -> {
            InvalidFieldDTO errorFieldDTO = InvalidFieldDTO.builder()
                    .name(invalidField.getField())
                    .description(invalidField.getDefaultMessage())
                    .value(String.valueOf(invalidField.getRejectedValue()))
                    .build();

            fields.add(errorFieldDTO);
        });

        ErrorMessageDTO errorMessageDTO = createMessageDTO(runtimeErrorEnum, httpStatus, request, fields);
        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorMessageDTO errorMessageDTO = createMessageDTO(exception.getError(), httpStatus, request, new ArrayList<>());
        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessageDTO> dataIntegrityViolation(DataIntegrityViolationException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorMessageDTO errorMessageDTO = createMessageDTO(exception.getError(), httpStatus, request, new ArrayList<>());
        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    private ErrorMessageDTO createMessageDTO(RuntimeErrorEnum runtimeErrorEnum, HttpStatus httpStatus, HttpServletRequest request, List<InvalidFieldDTO> fields) {
        return ErrorMessageDTO.builder()
                .code(runtimeErrorEnum.getError())
                .status(httpStatus.value())
                .message(runtimeErrorEnum.getDescription())
                .moment(Instant.now())
                .path(request.getRequestURI())
                .invalidFields(fields)
                .build();
    }
}
