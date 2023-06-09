package com.application.a3.domain.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.a3.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query(value = "SELECT * FROM Produto WHERE fornecedor_id = :fornecedor AND nome = :nome", nativeQuery = true)
	List<Produto> checarExistencia(@Param("fornecedor") String fornecedor, @Param("nome") String nome);
}
