package com.superdev.helpdesk.exceptions;

import org.springframework.http.HttpStatus;

public class RegraDeNegocio extends ErroAplicacao {
    public RegraDeNegocio(String mensagem) {
        super(HttpStatus.UNPROCESSABLE_CONTENT, "regra_negocio", mensagem);
    }
}