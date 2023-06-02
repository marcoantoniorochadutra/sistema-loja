package com.application.a3.service;

import java.util.List;

import com.application.a3.domain.entity.Produto;
import com.application.a3.model.dto.ProdutoDto;

public interface ProdutoService {

	public ProdutoDto cadastrarProduto(ProdutoDto dto);

	public void excluirProduto(Integer id);

	public ProdutoDto atualizarProduto(Integer id, ProdutoDto dto);
	
	public Produto buscarProduto(Integer dto);
	
	public List<Produto> listarProdutos();
	
}