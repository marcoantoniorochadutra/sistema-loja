package com.application.a3.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.a3.domain.entity.Fornecedor;
import com.application.a3.model.dto.FornecedorDto;
import com.application.a3.model.ref.TipoEntidade;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FornecedorServiceTest {

	private final FornecedorService fornecedorService;

	@Autowired
	public FornecedorServiceTest(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@Test
	@Order(1)
	public void deveCadastrarFornecedor() {

		FornecedorDto fornecedor = new FornecedorDto();
		fornecedor.setCadastroNacional("12345678900");
		fornecedor.setNome("Marco Dutra");
		fornecedor.setApelido("Marquinho");
		fornecedor.setEmail("marcordvida@hotmail.com");
		fornecedor.setNumeroCelular("48984654589");
		fornecedor.setNumeroTelefone("4832550704");
		fornecedor.setTipo(TipoEntidade.FISICA);

		FornecedorDto dto = fornecedorService.cadastrarFornecedor(fornecedor);

		assertEquals(fornecedor.getCadastroNacional(), dto.getCadastroNacional());
		assertEquals(fornecedor.getNome(), dto.getNome());
		assertEquals(fornecedor.getNumeroCelular(), dto.getNumeroCelular());
		assertEquals(fornecedor.getNumeroTelefone(), dto.getNumeroTelefone());
		assertEquals(fornecedor.getTipo(), dto.getTipo());
		assertEquals(fornecedor.getApelido(), dto.getApelido());
		assertEquals(fornecedor.getEmail(), dto.getEmail());

	}

	@Test
	@Order(2)
	public void deveAtualizarFornecedor() {
		
		FornecedorDto fornecedor = new FornecedorDto();
		fornecedor.setCadastroNacional("12345678900");
		fornecedor.setNome("Marco Antônio Dutra");
		fornecedor.setNumeroCelular("48984654589");
		fornecedor.setNumeroTelefone("4832550704");
		fornecedor.setTipo(TipoEntidade.FISICA);
		fornecedor.setApelido("Marquinho");
		fornecedor.setEmail("marcordvida@hotmail.com");
		
		
		
		FornecedorDto dto = fornecedorService.atualizarFornecedor(1, fornecedor);

		assertEquals(fornecedor.getCadastroNacional(), dto.getCadastroNacional());
		assertEquals(fornecedor.getNome(), dto.getNome());
		assertEquals(fornecedor.getNumeroCelular(), dto.getNumeroCelular());
		assertEquals(fornecedor.getNumeroTelefone(), dto.getNumeroTelefone());
		assertEquals(fornecedor.getTipo(), dto.getTipo());
		assertEquals(fornecedor.getApelido(), dto.getApelido());
		assertEquals(fornecedor.getEmail(), dto.getEmail());
	}
 
	@Test
	@Order(3)
	public void deveListarFornecedor() {

		FornecedorDto fornecedor = new FornecedorDto();
		fornecedor.setCadastroNacional("98765432100");
		fornecedor.setNome("Matheus Damazio");
		fornecedor.setNumeroCelular("48970707070");
		fornecedor.setNumeroTelefone("4832557070");
		fornecedor.setTipo(TipoEntidade.FISICA);
		fornecedor.setApelido("Batata");
		fornecedor.setEmail("batata@hotmail.com");
		
		fornecedorService.cadastrarFornecedor(fornecedor);

		List<Fornecedor> listaFornecedor = fornecedorService.listarFornecedor();

		assertEquals(2, listaFornecedor.size());
		assertEquals("Marco Antônio Dutra", listaFornecedor.get(0).getNome());
		assertEquals("Matheus Damazio", listaFornecedor.get(1).getNome());

	}

	@Test
	@Order(4)
	public void deveExcluirFornecedor() {


		fornecedorService.excluirFornecedor(1);

		List<Fornecedor> listaFornecedor = fornecedorService.listarFornecedor();

		assertEquals(1, listaFornecedor.size());
		assertEquals("Matheus Damazio", listaFornecedor.get(0).getNome());

	}
	
	@Test
	@Order(5)
	public void deveBuscarFornecedorPorCadastroNacional() {

		
		Fornecedor fornecedor = fornecedorService.buscarFornecedor("98765432100");

		assertEquals("Matheus Damazio", fornecedor.getNome());
		assertEquals("98765432100", fornecedor.getCadastroNacional());

	}	
	
	private void cadastrarFornecedor(String nome, String cadastroNacional) {
		FornecedorDto fornecedor = new FornecedorDto();
		fornecedor.setCadastroNacional(cadastroNacional);
		fornecedor.setNome(nome);
		fornecedor.setApelido("Apelido de " + nome);
		fornecedor.setEmail("marcordvida@hotmail.com");
		fornecedor.setNumeroCelular("48984654589");
		fornecedor.setNumeroTelefone("4832550704");
		fornecedor.setTipo(TipoEntidade.FISICA);
		fornecedorService.cadastrarFornecedor(fornecedor);
	}

}
