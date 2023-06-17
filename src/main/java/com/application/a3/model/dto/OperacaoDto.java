package com.application.a3.model.dto;

import java.util.Date;
import java.util.List;

import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OperacaoDto {

	private List<Produto> produtos;
	private Date dataVenda;
	private Double valorTotal;
	private Usuario usuario;
}
