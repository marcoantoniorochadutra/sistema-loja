package com.application.a3.model.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoOperacao {

	CRIACAO(0, "%s foi criado com sucesso"),
	EDICAO(1, "%s foi atualizado com sucesso"),
	EXCLUSAO(2, "%s foi excluído com sucesso"),
	ESTOQUE(3, "Foi adicionado %s ao estoque de %s"),
	VENDA(4, "Removido do estoque: \n%s"),
	REEMBOLSO(5, "Adicionado ao estoque: \n%s");

	private Integer code;
	private String value;
}
