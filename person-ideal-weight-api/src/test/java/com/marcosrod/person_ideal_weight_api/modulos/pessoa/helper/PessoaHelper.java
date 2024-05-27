package com.marcosrod.person_ideal_weight_api.modulos.pessoa.helper;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaResponse;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.Sexo;

import java.time.LocalDate;
import java.util.List;

public class PessoaHelper {

    public static List<PessoaResponse> umaListaPessoaResponse() {
        return List.of(umaPessoaResponse());
    }

    public static PessoaResponse umaPessoaResponse() {
        return new PessoaResponse(1L, "Pessoa1", LocalDate.of(1993, 12, 12),
                "123.123.123-12", Sexo.M, 1.85, 85.51);
    }
}
