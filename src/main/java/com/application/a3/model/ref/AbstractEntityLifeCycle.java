package com.application.a3.model.ref;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class AbstractEntityLifeCycle implements CrudEntity {
	@Column(nullable = false)
	private Date dataCriacao;
	@Column(nullable = false)
	private boolean ativo;

	@Override
	public Boolean getAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
		
	}
	

	
	


	

	


	
	

}
