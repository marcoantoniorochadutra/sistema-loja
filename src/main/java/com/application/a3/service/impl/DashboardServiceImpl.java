package com.application.a3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.domain.entity.Operacao;
import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.jpa.repository.OperacaoRepository;
import com.application.a3.domain.jpa.repository.ProdutoRepository;
import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.DashboardResultDto;
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
	public DashboardResultDto buildDashboard(Date startDate, Date endDate) {

		return new DashboardResultDto(valorEstoqueTotal(), lucroEstoqueTotal(), maisVendidos(),
				lucroFiltro(startDate, endDate));
	}

	private Double valorEstoqueTotal() {
		List<Produto> produto = produtoRepository.findAll();
		Double valorTotal = 0.0;

		for (Produto prod : produto) {
			valorTotal += (prod.getQuantidade() * prod.getValorCusto());
		}

		return valorTotal;
	}

	private Double lucroEstoqueTotal() {
		List<Produto> produto = produtoRepository.findAll();
		Double valorTotal = 0.0;

		for (Produto prod : produto) {
			valorTotal += (prod.getQuantidade() * (prod.getValorVenda() - prod.getValorCusto()));
		}

		return valorTotal;
	}

	private List<Produto> maisVendidos() {
		Map<Integer, Integer> maisVendidos = new HashMap<>();
		List<Produto> listaProdutos = new ArrayList<Produto>();
		List<Operacao> operacao = operacaoRepository.findAll();

		if (Objects.nonNull(operacao)) {
			operacao.forEach(o -> o.getProdutos().forEach(p -> {
				if (maisVendidos.containsKey(p.getId())) {
					Integer qtdAtual = maisVendidos.get(p.getId());
					maisVendidos.put(p.getId(), qtdAtual + p.getQuantidade());
				} else {
					maisVendidos.put(p.getId(), p.getQuantidade());
				}
			}));
		}
		maisVendidos.forEach((t, u) -> {
			Produto p = produtoRepository.findById(t).get();
			if (Objects.nonNull(p)) {
				p.setQuantidade(u);
				listaProdutos.add(p);
			} else {
				throw new GenericException(ExceptionReturnMessage.ARGUMENTO_INVALIDO);
			}

		});
		listaProdutos.sort((o1, o2) -> o2.getQuantidade().compareTo(o1.getQuantidade()));
		return listaProdutos;
	}

	public Double lucroFiltro(Date startDate, Date endDate) {
		List<Operacao> operacaoList = operacaoRepository.findByDataCriacaoBetween(startDate, endDate);
		AtomicReference<Double> atomicDouble = new AtomicReference<Double>(0.0);
		operacaoList.forEach(operacao -> {
			operacao.getProdutos().forEach(p -> {
				Double lucro = p.getValorVenda() - p.getValorCusto();
				atomicDouble.updateAndGet(valorAtual -> valorAtual += lucro);
			});
		});

		return atomicDouble.get();

	}

}
