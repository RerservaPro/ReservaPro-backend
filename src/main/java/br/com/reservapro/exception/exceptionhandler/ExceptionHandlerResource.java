package br.com.reservapro.exception.exceptionhandler;

import br.com.reservapro.exception.DataIntegrityViolationException;
import br.com.reservapro.exception.ResourceNotFoundException;
import br.com.reservapro.exception.UnauthorizedException;
import br.com.reservapro.exception.UnprocessableEntityException;
import br.com.reservapro.exception.enums.RuntimeErrorEnum;
import br.com.reservapro.exception.exceptionhandler.dto.ErrorMessageDTO;
import br.com.reservapro.exception.exceptionhandler.dto.InvalidFieldDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<ErrorMessageDTO> handleUnauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ErrorMessageDTO.builder()
                        .moment(Instant.now())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .message(exception.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorMessageDTO> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErrorMessageDTO.builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .message(ex.getMessage())
                        .moment(Instant.now())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ErrorMessageDTO handleException(Exception ex) {
        return ErrorMessageDTO.builder()
                .moment(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
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
