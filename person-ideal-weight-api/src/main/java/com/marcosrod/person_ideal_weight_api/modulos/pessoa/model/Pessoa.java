package com.marcosrod.person_ideal_weight_api.modulos.pessoa.model;

import com.marcosrod.person_ideal_weight_api.modulos.pessoa.dto.PessoaResponse;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.enums.Sexo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity(name = "Pessoa")
public class Pessoa {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "cpf")
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
	private Sexo sexo;

	@Column(name = "altura")
	private Double altura;

	@Column(name = "peso")
	private Double peso;

	public Pessoa(String nome, LocalDate dataNascimento, String cpf, Sexo sexo, Double altura, Double peso) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
	}

	public Pessoa() {

	}

	public String calcularPesoIdeal(Double primeiroOperador, Double segundoOperador) {
		return new StringBuilder()
				.append("Peso ideal: ")
				.append(BigDecimal.valueOf((primeiroOperador * altura) - segundoOperador)
						.setScale(2, RoundingMode.HALF_UP)
						.doubleValue())
				.append(" Kg")
				.toString();
	}

	public PessoaResponse toResponse() {
		return new PessoaResponse(this.id, this.nome, this.dataNascimento, this.cpf,
				this.sexo, this.altura, this.peso);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Double getAltura() {
		return altura;
	}

	public Double getPeso() {
		return peso;
	}
}
