package br.com.reservapro.infrastructure.controller.servico.dto;

import br.com.reservapro.domain.pagination.StatusAtivacaoDTO;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ServicoDTO(
        String id,
        @NotBlank(groups = {OnCreate.class}, message = "O nome deve ser preenchido.")
        String nome,
        @NotNull(groups = {OnCreate.class}, message = "O preço não deve ser nulo.")
        @Max(groups = {OnCreate.class, OnUpdate.class}, value = 1_000_000_000, message = "O preço não pode ultrapasar 1 bilhão.")
        @PositiveOrZero(groups = {OnCreate.class, OnUpdate.class}, message = "O preço não pode ser negativo..")
        BigDecimal preco,
        @NotBlank(groups = {OnCreate.class}, message = "A descrição deve ser preenchido.")
        String descricao,
        StatusAtivacaoDTO statusAtivacao
) {
    public interface OnCreate {}
    public interface OnUpdate {}
}
