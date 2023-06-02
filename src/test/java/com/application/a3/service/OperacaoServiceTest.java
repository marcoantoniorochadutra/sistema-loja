package com.application.a3.service;

import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.entity.Usuario;
import com.application.a3.domain.jpa.repository.ProdutoRepository;
import com.application.a3.domain.jpa.repository.UsuarioRepository;
import com.application.a3.model.dto.OperacaoDto;
import com.application.a3.model.ref.TipoUsuario;

@SpringBootTest
public class OperacaoServiceTest {
	
		
	private final ProdutoRepository produtoRepository;
	private final OperacaoService operacaoService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	public OperacaoServiceTest(ProdutoRepository produtoRepository, OperacaoService operacaoService) {
		this.produtoRepository = produtoRepository;
		this.operacaoService = operacaoService;
	}



	@Test
	public void deveCadastrarVenda() {
		Usuario u = new Usuario();
		u.setId(1);
		u.setNome("Marco");
		u.setCadastroNacional("00300600900");
		u.setNumeroCelular("000000000");
		u.setNumeroTelefone("000000000");
		u.setTipo(TipoUsuario.GERENTE);
		repository.save(u);
		
		OperacaoDto dto = new OperacaoDto();		
		dto.setDataVenda(Instant.now());
		dto.setValorTotal(8600.0);
		dto.setUsuario(u);
		dto.setProdutos(new ArrayList<>());
		
		Produto p1 = produtoRepository.findById(7).get();
		p1.setQuantidade(1);
		Produto p2 = produtoRepository.findById(8).get();
		p2.setQuantidade(1);
		dto.getProdutos().add(p1);
		dto.getProdutos().add(p2);
		
		operacaoService.efetuarVenda(dto);
		
		

		
	}

}
