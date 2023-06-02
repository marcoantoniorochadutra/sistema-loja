package com.application.a3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.jpa.repository.OperacaoRepository;
import com.application.a3.domain.jpa.repository.ProdutoRepository;
import com.application.a3.service.DashboardService;


@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class DashboardServiceImpl implements DashboardService {

	private final ProdutoRepository produtoRepository;
	private final OperacaoRepository operacaoRepository;

	@Autowired
	public DashboardServiceImpl(ProdutoRepository produtoRepository, OperacaoRepository operacaoRepository) {
		this.produtoRepository = produtoRepository;
		this.operacaoRepository = operacaoRepository;
	}

	@Override
	public Double valorEstoqueTotal() {
		List<Produto> produto = produtoRepository.findAll();
		Double valorTotal = 0.0;

		for (Produto prod : produto) {
			valorTotal += (prod.getQuantidade() * prod.getValorCusto());
		}

		return valorTotal;
	}

	@Override
	public Double lucroEstoqueTotal() {
		List<Produto> produto = produtoRepository.findAll();
		Double valorTotal = 0.0;

		for (Produto prod : produto) {
			valorTotal += (prod.getQuantidade() * (prod.getValorVenda() - prod.getValorCusto()));
		}

		return valorTotal;
	}

}
