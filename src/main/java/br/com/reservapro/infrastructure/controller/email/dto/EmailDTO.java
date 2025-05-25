package br.com.reservapro.infrastructure.controller.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @NotBlank(message = "O campo email deve ser preenchido.")
        @Email(message = "O formato do e-mail não está válido.")
        String to,
        @NotBlank(message = "O campo assunto deve ser preenchido.")
        String subject,
        @NotBlank(message = "O campo conteúdo deve ser preenchido.")
        String content
) {

}
