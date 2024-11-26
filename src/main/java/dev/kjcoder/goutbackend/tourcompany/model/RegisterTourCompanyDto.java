package dev.kjcoder.goutbackend.tourcompany.model;

import jakarta.validation.constraints.NotBlank;

public record RegisterTourCompanyDto(
        Integer tourCompanyId,
        @NotBlank String tourCompanyName,
        String status
) {
}
