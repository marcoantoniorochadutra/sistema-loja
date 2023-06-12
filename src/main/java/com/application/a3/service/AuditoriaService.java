package com.application.a3.service;

import java.util.List;

import com.application.a3.domain.entity.Auditoria;

public interface AuditoriaService {
	
	public List<Auditoria> buscarAuditoriaProduto();

	public List<Auditoria> buscarAuditoriaFornecedor();
	
	public List<Auditoria> buscarAuditoriaOperacao();

}
