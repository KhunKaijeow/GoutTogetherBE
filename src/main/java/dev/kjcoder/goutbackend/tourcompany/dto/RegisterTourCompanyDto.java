package dev.kjcoder.goutbackend.tourcompany.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterTourCompanyDto(
        Integer tourCompanyId,
        @NotBlank String tourCompanyName,
        @NotBlank String username,
        @NotBlank String password,
        String status
) {
}
