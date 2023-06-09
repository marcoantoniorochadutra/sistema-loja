package com.application.a3.web.endpoint;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.a3.domain.entity.Produto;
import com.application.a3.model.dto.ProdutoDto;
import com.application.a3.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@Singleton
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping("/cadastrar")
	public ProdutoDto cadastrarProduto(@Valid @RequestBody ProdutoDto dto) {
		return produtoService.cadastrarProduto(dto);
	}

	@PutMapping("/atualizar/{id}")
	public ProdutoDto atualizarProduto(@PathVariable("id") Integer id, @Valid @RequestBody ProdutoDto dto) {
		return produtoService.atualizarProduto(id, dto);
	}

	@GetMapping("/buscar/{id}")
	public Produto buscarProduto(@PathVariable("id") Integer id) {
		return produtoService.buscarProduto(id);
	}

	@GetMapping("/listar")
	public List<Produto> listarProdutos() {
		return produtoService.listarProdutos();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletarProduto(@PathVariable("id") Integer id) {
		produtoService.excluirProduto(id);
	}
}
