package com.application.a3.domain.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.a3.domain.entity.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Integer> {
	
	List<Operacao> findByDataCriacaoBetween(Date startDate, Date endDate);

}
