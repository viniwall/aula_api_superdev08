package com.superdev.helpdesk.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TicketCancelarDto(
        @NotBlank
        @Size (min = 10, max = 5000)
        String motivoCancelamento
) {
}
