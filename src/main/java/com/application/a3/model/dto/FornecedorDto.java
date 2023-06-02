package com.application.a3.model.dto;

import com.application.a3.model.ref.TipoEntidade;
import com.application.a3.utils.CadastroNacionalHelper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FornecedorDto {
	
	private String nome;
	private String apelido;
	private String email;	
	private String cadastroNacional;
	private String numeroTelefone;
	private String numeroCelular;
	private TipoEntidade tipo;

	
	public FornecedorDto(String nome, String apelido, String email, String cadastroNacional, String numeroTelefone,
			String numeroCelular, TipoEntidade tipo) {
		super();
		this.nome = nome;
		this.apelido = apelido;
		this.email = email;
		this.cadastroNacional = CadastroNacionalHelper.removeMask(numeroCelular);
		this.numeroTelefone = numeroTelefone;
		this.numeroCelular = numeroCelular;
		this.tipo = tipo;
	}

	
	
}
