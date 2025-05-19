package br.com.reservapro.exceptions.exceptionhandler;

import br.com.reservapro.exceptions.RecursoNaoEncontradoException;
import br.com.reservapro.exceptions.UnauthorizedException;
import br.com.reservapro.exceptions.UnprocessableEntityException;
import br.com.reservapro.exceptions.ViolacaoIntegridadeDadoException;
import br.com.reservapro.exceptions.enums.RuntimeErroEnum;
import br.com.reservapro.exceptions.exceptionhandler.dto.CampoInvalidodDTO;
import br.com.reservapro.exceptions.exceptionhandler.dto.MensagemErroDTO;
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
    public ResponseEntity<MensagemErroDTO> argumentoMetodoNaoValido(MethodArgumentNotValidException excecao, HttpServletRequest request) {
        RuntimeErroEnum erroEnum = RuntimeErroEnum.ERR0001;
        List<CampoInvalidodDTO> campos = new ArrayList<>();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        excecao.getBindingResult().getFieldErrors().forEach(campoInvalido -> {
            CampoInvalidodDTO errorFieldDTO = CampoInvalidodDTO.builder()
                    .nome(campoInvalido.getField())
                    .descricao(campoInvalido.getDefaultMessage())
                    .valor(String.valueOf(campoInvalido.getRejectedValue()))
                    .build();

            campos.add(errorFieldDTO);
        });

        MensagemErroDTO errorMessageDTO = MensagemErroDTO.builder()
                .codigo(erroEnum.getErro())
                .status(httpStatus.value())
                .mensagem(erroEnum.getDescricao())
                .momento(Instant.now())
                .caminho(request.getRequestURI())
                .camposInvalidos(campos)
                .build();

        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<MensagemErroDTO> RecursoNaoEncontrado(RecursoNaoEncontradoException excecao, HttpServletRequest request) {
        RuntimeErroEnum runtimeErroEnum = excecao.getErro();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        MensagemErroDTO mensagemErroDTO = MensagemErroDTO.builder()
                .codigo(runtimeErroEnum.getErro())
                .status(httpStatus.value())
                .mensagem(runtimeErroEnum.getDescricao())
                .momento(Instant.now())
                .caminho(request.getRequestURI())
                .build();

        return ResponseEntity.status(httpStatus).body(mensagemErroDTO);
    }

    @ExceptionHandler(ViolacaoIntegridadeDadoException.class)
    public ResponseEntity<MensagemErroDTO> violacaoIntegridadeDado(ViolacaoIntegridadeDadoException excecao, HttpServletRequest request) {
        RuntimeErroEnum runtimeErrorEnum = excecao.getErro();
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        MensagemErroDTO errorMessageDTO = MensagemErroDTO.builder()
                .codigo(runtimeErrorEnum.getErro())
                .status(httpStatus.value())
                .mensagem(runtimeErrorEnum.getDescricao())
                .momento(Instant.now())
                .caminho(request.getRequestURI())
                .build();

        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<MensagemErroDTO> handleUnauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                MensagemErroDTO.builder()
                        .momento(Instant.now())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .mensagem(exception.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<MensagemErroDTO> handleUnprocessableEntityException(UnprocessableEntityException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(MensagemErroDTO.builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .mensagem(ex.getMessage())
                        .momento(Instant.now())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public MensagemErroDTO handleException(Exception ex) {
        return MensagemErroDTO.builder()
                .momento(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensagem(ex.getMessage())
                .build();
    }
}
