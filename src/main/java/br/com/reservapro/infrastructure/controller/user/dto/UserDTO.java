package br.com.reservapro.infrastructure.controller.user.dto;


import br.com.reservapro.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public record UserDTO ( @JsonProperty("name")
                       @NotBlank(message = "The Company's name is mandatory")
                       String name,
                       @NotBlank(message = "Email is" +
                               " mandatory")
                       @Email(message = "it must be a well-formed email address")
                       String email,
                        @NotBlank(message = "document is mandatory")
                        @CNPJ(groups = org.hibernate.validator.constraints.br.CNPJ.class)
                        @CPF(groups = org.hibernate.validator.constraints.br.CPF.class)
                        String cpfOuCnpj,
                       @NotBlank(message = "Password is mandatory")
                       @Size(min = 8, max = 16, message = "Password size must be between 8 and 16")
                       @Pattern.List({
                               @Pattern(
                                       regexp = ".*[a-z].*",
                                       message = "Password must contain at least one lowercase letter"
                               ),
                               @Pattern(
                                       regexp = ".*[A-Z].*",
                                       message = "Password must contain at least one uppercase letter"
                               ),
                               @Pattern(
                                       regexp = ".*[0-9].*",
                                       message = "Password must contain at least one digit"
                               ),
                               @Pattern(
                                       regexp = ".*[@#$%^&+=!].*",
                                       message = "Password must contain at least one special character"
                               )
                       })
                       String password,
                       Role role) {
}
