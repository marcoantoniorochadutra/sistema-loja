package com.application.a3.model.dto;

import java.util.List;

import com.application.a3.domain.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DashboardResultDto {
	
	private Double valorTotal;
	private Double valorLucroTotal;
	private List<Produto> maisVendidos;
	private Double valorFiltro;
}
