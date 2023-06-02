package com.application.a3.model.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUsuario {
	
	COLABORADOR(0, "Colaborador"),
	GERENTE(1, "Gerente");
	
	private Integer code;
	private String value;
}
