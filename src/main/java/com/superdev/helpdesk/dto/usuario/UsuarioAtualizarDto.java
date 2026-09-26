package com.superdev.helpdesk.dto.usuario;

import com.superdev.helpdesk.enums.Papel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioAtualizarDto (
        @Schema(description="Define o nome do usuário", example="Ana da Silva")
        @NotBlank @Size(min=2, max=60)
        String nome,

        @Schema(description="Define o email do usuário", example="ana@tickets.com.br")
        @Size(max=254)
        String email,

        @Schema(description = "Define o papel do usuário como SOLICITANTE ou ATENDENTE", example="SOLICITANTE")
        Papel papel
){}
