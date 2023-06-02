package com.application.a3.model.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEntidade {
	FISICA(0, "Pessoa Física"),
	JURIDICA(1, "Pessoa Jurídica");
	
	private Integer code;
	private String value;

	
	
}
