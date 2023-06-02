package com.application.a3.model.dto;

import com.application.a3.model.ref.TipoOperacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoDto {

	private String nome;
	private Double valorCusto;
	private Double valorVenda;
	private Integer quantidade;
	private String fornecedorCadastroNacional;
	private TipoOperacao tipoOperacao;

}
