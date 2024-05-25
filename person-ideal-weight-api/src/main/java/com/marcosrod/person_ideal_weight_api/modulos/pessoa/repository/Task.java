package com.marcosrod.person_ideal_weight_api.modulos.pessoa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marcosrod.person_ideal_weight_api.modulos.pessoa.model.Pessoa;

@Repository
public interface Task extends JpaRepository<Pessoa, Long>{
}
