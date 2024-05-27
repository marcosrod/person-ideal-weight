package com.marcosrod.person_ideal_weight_api.modulos.pessoa.controller;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.comum.exception.ValidacaoException;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.service.PessoaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.PessoaErro.PESSOA_NAO_ENCONTRADA;
import static com.marcosrod.person_ideal_weight_api.modulos.pessoa.helper.PessoaHelper.umaListaPessoaResponse;
import static com.marcosrod.person_ideal_weight_api.modulos.pessoa.helper.PessoaHelper.umaPessoaResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

    private static final Long ID_UM = 1L;
    private static final String API_URI = "/api/pessoas/";
    private static final String URI_GET_BY_ID = API_URI.concat(String.valueOf(ID_UM));

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PessoaService service;

    @Test
    @SneakyThrows
    void pesquisar_deveRetornarListaPessoaResponse_quandoSolicitado() {
        doReturn(umaListaPessoaResponse()).when(service).pesquisar();
        var pessoaExpected = umaPessoaResponse();

        mvc.perform(MockMvcRequestBuilders.get(API_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(pessoaExpected.id()))
                .andExpect(jsonPath("$[0].nome").value(pessoaExpected.nome()))
                .andExpect(jsonPath("$[0].dataNascimento").value(pessoaExpected.dataNascimento().toString()))
                .andExpect(jsonPath("$[0].cpf").value(pessoaExpected.cpf()))
                .andExpect(jsonPath("$[0].sexo").value(pessoaExpected.sexo().name()))
                .andExpect(jsonPath("$[0].altura").value(pessoaExpected.altura()))
                .andExpect(jsonPath("$[0].peso").value(pessoaExpected.peso()));

        verify(service).pesquisar();
    }

    @Test
    @SneakyThrows
    void pesquisar_deveRetornarListaVazia_quandoNaoHouveremPessoasCadastradas() {
        doReturn(List.of()).when(service).pesquisar();

        mvc.perform(MockMvcRequestBuilders.get(API_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verify(service).pesquisar();
    }

    @Test
    @SneakyThrows
    void pesquisarPorId_deveRetornarPessoaResponse_quandoEncontrado() {
        var pessoaExpected = umaPessoaResponse();
        doReturn(pessoaExpected).when(service).pesquisarPorId(ID_UM);

        mvc.perform(MockMvcRequestBuilders.get(URI_GET_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pessoaExpected.id()))
                .andExpect(jsonPath("$.nome").value(pessoaExpected.nome()))
                .andExpect(jsonPath("$.dataNascimento").value(pessoaExpected.dataNascimento().toString()))
                .andExpect(jsonPath("$.cpf").value(pessoaExpected.cpf()))
                .andExpect(jsonPath("$.sexo").value(pessoaExpected.sexo().name()))
                .andExpect(jsonPath("$.altura").value(pessoaExpected.altura()))
                .andExpect(jsonPath("$.peso").value(pessoaExpected.peso()));

        verify(service).pesquisarPorId(eq(ID_UM));
    }

    @Test
    @SneakyThrows
    void pesquisarPorId_deveLancarException_quandoPessoaNaoEncontrada() {
        doThrow(new ValidacaoException(PESSOA_NAO_ENCONTRADA.getDescricao()))
                .when(service).pesquisarPorId(ID_UM);

        mvc.perform(MockMvcRequestBuilders.get(URI_GET_BY_ID))
                .andExpect(status().isInternalServerError())
                .andExpect(retorno -> assertTrue(retorno.getResolvedException() instanceof ValidacaoException))
                .andExpect(retorno -> assertEquals(retorno.getResolvedException().getMessage(),
                        PESSOA_NAO_ENCONTRADA.getDescricao()));

        verify(service).pesquisarPorId(eq(ID_UM));
    }
}
