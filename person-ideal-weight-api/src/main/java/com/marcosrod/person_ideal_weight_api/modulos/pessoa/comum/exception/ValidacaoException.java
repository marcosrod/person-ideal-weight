package com.marcosrod.person_ideal_weight_api.modulos.pessoa.comum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String message) {
        super(message);
    }
}
