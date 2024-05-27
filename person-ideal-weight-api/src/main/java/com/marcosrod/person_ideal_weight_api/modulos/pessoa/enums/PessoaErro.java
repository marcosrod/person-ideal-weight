package com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums;

public enum PessoaErro {

    PESSOA_NAO_ENCONTRADA("A pessoa requisitada não existe."),
    ERRO_AO_EXCLUIR_PESSOA("Não foi possível excluir a pessoa: ");

    private String descricao;

    PessoaErro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
