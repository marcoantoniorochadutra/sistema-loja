package com.application.a3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.domain.entity.Auditoria;
import com.application.a3.domain.jpa.repository.AuditoriaRepository;
import com.application.a3.service.AuditoriaService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class AuditoriaServiceImpl implements AuditoriaService{

	private final AuditoriaRepository auditoriaRepository;
		
	@Autowired
	public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository) {
		this.auditoriaRepository = auditoriaRepository;
	}

	@Override
	public List<Auditoria> buscarAuditoriaProduto() {
		return auditoriaRepository.buscarAuditoriaProduto();
	}

	@Override
	public List<Auditoria> buscarAuditoriaFornecedor() {
		return auditoriaRepository.buscarAuditoriaFornecedor();
	}




	
}
