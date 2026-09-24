package com.superdev.helpdesk.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCriarDto (
    @NotBlank @Size(min=2, max=60)
    String nome,

    @Size(max=254)
    String email
){}
