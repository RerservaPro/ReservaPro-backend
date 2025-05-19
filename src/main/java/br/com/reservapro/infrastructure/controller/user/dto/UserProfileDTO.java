package br.com.reservapro.infrastructure.controller.user.dto;

import lombok.Builder;

@Builder
public record UserProfileDTO(String id, String name, String lastname, String email, String avatar) {
}
