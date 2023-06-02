package com.application.a3.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.a3.domain.entity.Produto;
import com.application.a3.model.dto.ProdutoDto;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProdutoServiceTest {


	private final ProdutoService produtoService;

	@Autowired
	public ProdutoServiceTest(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Test
	@Order(1)
	public void deveCadastrarProduto() {

		ProdutoDto dto = new ProdutoDto();
		dto.setFornecedorCadastroNacional("12345678900");
		dto.setNome("SAMSUNG S21 - 128 GB");
		dto.setQuantidade(5);
		dto.setValorCusto(2200.0);
		dto.setValorVenda(3400.0);

		ProdutoDto prod = produtoService.cadastrarProduto(dto);

		assertEquals(dto.getFornecedorCadastroNacional(), prod.getFornecedorCadastroNacional());
		assertEquals(dto.getNome(), prod.getNome());
		assertEquals(dto.getQuantidade(), prod.getQuantidade());
		assertEquals(dto.getValorCusto(), prod.getValorCusto());
		assertEquals(dto.getValorVenda(), prod.getValorVenda());

	}

	@Test
	@Order(2)
	public void deveAtualizarProduto() {
		
		ProdutoDto dto = new ProdutoDto();
		dto.setFornecedorCadastroNacional("98765432100");
		dto.setNome("SAMSUNG S10 - 128 GB");
		dto.setQuantidade(10);
		dto.setValorCusto(2200.0);
		dto.setValorVenda(3500.0);			
		
		ProdutoDto prod = produtoService.atualizarProduto(2, dto);
		
		assertEquals(dto.getFornecedorCadastroNacional(), prod.getFornecedorCadastroNacional());
		assertEquals(dto.getNome(), prod.getNome());
		assertEquals(dto.getQuantidade(), prod.getQuantidade());
		assertEquals(dto.getValorCusto(), prod.getValorCusto());
		assertEquals(dto.getValorVenda(), prod.getValorVenda());
	}

	@Test
	@Order(3)
	public void deveListarProduto() {

		List<Produto> listaProduto = produtoService.listarProdutos();
		listaProduto.forEach(t -> {
			System.out.println(t);
		});
		assertEquals(2, listaProduto.size());
		assertEquals("SAMSUNG S10 - 128 GB", listaProduto.get(0).getNome());
		assertEquals("SAMSUNG S20 - 128 GB", listaProduto.get(1).getNome());
	}
	@Test
	@Order(4)
	public void deveExcluirProduto() {

		produtoService.excluirProduto(1);
		
		List<Produto> listaProduto = produtoService.listarProdutos();

		assertEquals(1, listaProduto.size());
		assertEquals("SAMSUNG 20 - 128 GB", listaProduto.get(0).getNome());
	}


}
