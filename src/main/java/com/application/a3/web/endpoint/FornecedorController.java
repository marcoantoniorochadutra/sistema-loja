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

import com.application.a3.domain.entity.Fornecedor;
import com.application.a3.model.dto.FornecedorDto;
import com.application.a3.service.FornecedorService;

import jakarta.validation.Valid;

@RestController
@Singleton
@RequestMapping("/fornecedor")
public class FornecedorController {

	private final FornecedorService fornecedorService;

	@Autowired
	private FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@PostMapping("/cadastrar")
	public FornecedorDto cadastrarProduto(@Valid @RequestBody FornecedorDto dto) {
		return fornecedorService.cadastrarFornecedor(dto);
	}

	@PutMapping("/atualizar/{id}")
	public FornecedorDto atualizarProduto(@PathVariable("id") Integer id, @Valid @RequestBody FornecedorDto dto) {
		return fornecedorService.atualizarFornecedor(id, dto);
	}

	@GetMapping("/buscar/{cadastroNacional}")
	public Fornecedor buscarProduto(@PathVariable("cadastroNacional") String cadastroNacional) {
		return fornecedorService.buscarFornecedor(cadastroNacional);

	}

	@GetMapping("/listar")
	public List<Fornecedor> listarProdutos() {
		return fornecedorService.listarFornecedor();
	}

	@DeleteMapping("/deletar/{id}")
	public void excluirFornecedor(@PathVariable("id") Integer id) {
		fornecedorService.excluirFornecedor(id);
	}

}
