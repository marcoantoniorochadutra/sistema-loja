package com.application.a3.service;

import java.util.List;

import com.application.a3.domain.entity.Fornecedor;
import com.application.a3.model.dto.FornecedorDto;

public interface FornecedorService {
	
	public FornecedorDto cadastrarFornecedor(FornecedorDto dto);

	public void excluirFornecedor(Integer id);

	public FornecedorDto atualizarFornecedor(Integer id, FornecedorDto dto);
	
	public Fornecedor buscarFornecedor(String cadastroNacional);
	
	public List<Fornecedor> listarFornecedor();

}
