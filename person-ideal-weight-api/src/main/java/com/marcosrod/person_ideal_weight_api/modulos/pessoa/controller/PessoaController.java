package com.marcosrod.person_ideal_weight_api.modulos.pessoa.controller;

import java.util.List;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaRequest;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.service.PessoaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired	
	private PessoaService service;
	
	@GetMapping
	public List<PessoaResponse> pesquisar() {
		return service.pesquisar();
	}

	@GetMapping("{id}")
	public PessoaResponse pesquisarPorId(@PathVariable("id") Long id) {
		return service.pesquisarPorId(id);
	}

	@GetMapping("{id}/peso-ideal")
	public String findPesoIdealById(@PathVariable("id") Long id) {
		return service.findPesoIdealById(id);
	}

	@PostMapping
	public void incluir(@RequestBody PessoaRequest dto) {
		service.incluir(dto);
	}

	@PutMapping("{id}")
	public void alterar(@RequestBody PessoaRequest dto, @PathVariable("id") Long id) {
		service.alterar(dto, id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable("id") Long id) {
		service.excluir(id);
	}
}
