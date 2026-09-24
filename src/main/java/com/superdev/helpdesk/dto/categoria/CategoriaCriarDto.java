package com.superdev.helpdesk.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
* Record é uma classe para armazenar dados imutáveis
* */
public record CategoriaCriarDto (
    @NotBlank @Size(min=2, max=60)
    String nome,

    @Size(max=225)
    String descricao

){}