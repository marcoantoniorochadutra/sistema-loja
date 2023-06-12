package com.application.a3.domain.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.a3.domain.entity.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer>{
	
	
	@Query(value="SELECT * FROM Auditoria WHERE tipo_entidade = 'produto'", nativeQuery = true)
	List<Auditoria> buscarAuditoriaProduto();
	
	@Query(value="SELECT * FROM Auditoria WHERE tipo_entidade = 'fornecedor'", nativeQuery = true)
	List<Auditoria> buscarAuditoriaFornecedor();
	
	@Query(value="SELECT * FROM Auditoria WHERE tipo_entidade = 'operacao'", nativeQuery = true)
	List<Auditoria> buscarAuditoriaOperacao();

}

