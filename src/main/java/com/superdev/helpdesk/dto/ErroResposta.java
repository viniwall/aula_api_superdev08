package com.superdev.helpdesk.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ErroResposta (
    @Schema(example = "nao encontrado")
    String codigo,

    @Schema(example = "categoria não encontrada")
    String mensagem,

    List<?> detalhes
    ) {
}

