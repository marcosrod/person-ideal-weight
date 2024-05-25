package com.marcosrod.person_ideal_weight_api.modulos.pessoa.service;

import java.util.List;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.Sexo;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaResponse;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.model.Pessoa;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.repository.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

	private final static Double OPERADOR_UM_PESO_MASCULINO = 72.7;
	private final static Double OPERADOR_DOIS_PESO_MASCULINO = 58.0;
	private final static Double OPERADOR_UM_PESO_FEMININO = 62.1;
	private final static Double OPERADOR_DOIS_PESO_FEMININO = 44.7;

	@Autowired
	private Task task;
	
	public List<PessoaResponse> pesquisar() {
		return task.findAll()
				.stream()
				.map(Pessoa::toResponse)
				.toList();
	}

	public PessoaResponse pesquisarPorId(Long id) {
		return findPessoaById(id).toResponse();
	}

	public String findPesoIdealById(Long id) {
		var pessoa = findPessoaById(id);
		if (pessoa.getSexo() == Sexo.M) {
			return pessoa.calcularPesoIdeal(OPERADOR_UM_PESO_MASCULINO, OPERADOR_DOIS_PESO_MASCULINO);
		} else {
			return pessoa.calcularPesoIdeal(OPERADOR_UM_PESO_FEMININO, OPERADOR_DOIS_PESO_FEMININO);
		}
	}
	
	public void incluir(PessoaRequest request) {
		var pessoa = new Pessoa();
		BeanUtils.copyProperties(request, pessoa);

		task.save(pessoa);
	}
	
	public void alterar(PessoaRequest request, Long id) {
		var pessoa = findPessoaById(id);
		BeanUtils.copyProperties(request, pessoa);

		task.save(pessoa);
	}
	
	public void excluir(Long id) {
		try {
			task.deleteById(id);
		} catch (DataIntegrityViolationException dive) {
			throw new RuntimeException("Nao foi possivel excluir o usuario: "
					.concat(dive.getMessage()));
		}
	}

	private Pessoa findPessoaById(Long id) {
		return task.findById(id)
				.orElseThrow(() -> new RuntimeException("O usuario requisitado nao existe."));
	}
}
