package com.application.a3.service;

import com.application.a3.model.dto.OperacaoDto;

public interface OperacaoService {
	
	public OperacaoDto efetuarVenda(OperacaoDto dto);
	
	public void efetuarReembolso(Integer id);
	
	
	

}
