package com.superdev.helpdesk.exceptions;
/*
* base das exceções de domínio. Os serviços lançam estas ecxeções sem saber
* nada de http; o TratadorDeErros traduz cada uma para status + corpo padrão
* */

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErroAplicacao extends RuntimeException {
    //Atributos protegidos com final para não permitir a mudança
    //com Getter (que permitirá somente a leitura), sem Setter + final
    private final HttpStatus status;
    private final String codigo;

    //Constructor
    protected ErroAplicacao(HttpStatus status, String codigo, String mensagem){
        // passando para o constructor da classe pai (runtime ecxeption) a mensagem
        super(mensagem);
        this.status = status;
        this.codigo = codigo;
    }
}
