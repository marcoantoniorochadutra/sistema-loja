package com.application.a3.model.ref;

import java.util.Date;

public interface CrudEntity{
	
	Boolean getAtivo();
	
	void setAtivo(Boolean ativo);

	Date getDataCriacao();
	
	void setDataCriacao(Date dataCriacao);

	
}
