package com.application.a3.domain.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.a3.domain.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
	
	Optional<Fornecedor> findByCadastroNacional(String cadastroNacional);

}
