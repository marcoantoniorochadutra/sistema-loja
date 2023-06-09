package com.application.a3.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.domain.entity.Operacao;
import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.jpa.base.AbstractJpaCrudService;
import com.application.a3.domain.jpa.repository.AuditoriaRepository;
import com.application.a3.domain.jpa.repository.OperacaoRepository;
import com.application.a3.domain.jpa.repository.ProdutoRepository;
import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.OperacaoDto;
import com.application.a3.service.OperacaoService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class OperacaoServiceImpl extends AbstractJpaCrudService<Operacao, OperacaoDto> implements OperacaoService {

	private final OperacaoRepository operacaoRepository;
	private final ProdutoRepository produtoRepository;

	@Autowired
	public OperacaoServiceImpl(AuditoriaRepository auditoriaRepository, OperacaoRepository operacaoRepository,
			ProdutoRepository produtoRepository) {
		super(auditoriaRepository);
		this.operacaoRepository = operacaoRepository;
		this.produtoRepository = produtoRepository;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected OperacaoRepository getRepository() {
		return operacaoRepository;
	}

	@Override
	protected Class<Operacao> getDomainClass() {
		return Operacao.class;
	}

	@Override
	protected Class<OperacaoDto> getModelClass() {
		return OperacaoDto.class;
	}

	@Override
	public OperacaoDto efetuarVenda(OperacaoDto dto) {
		StringBuilder errors = new StringBuilder();
		
		dto.getProdutos().forEach(p -> {
			Produto prod = produtoRepository.getReferenceById(p.getId());
			if (prod.getQuantidade() == 0 || p.getQuantidade() > prod.getQuantidade()) {
				errors.append("\n" + p.getNome() + " | Estoque: " + prod.getQuantidade());
			}
			prod.setQuantidade(prod.getQuantidade() - p.getQuantidade());
			produtoRepository.save(prod);
		});

		if (errors.isEmpty()) {
			return cadastrar(dto);
		} else {
			throw new GenericException(
					String.format(ExceptionReturnMessage.OPERACAO_FALHOU_SEM_ESTOQUE, errors.toString()));
		}

	}

	@Override
	public void efetuarReembolso(Integer id) {
		Optional<Operacao> operacaoTemp = getRepository().findById(id);
		if (operacaoTemp.isEmpty()) {
			throw new GenericException(String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, id));
		} else {
			Operacao operacao = operacaoTemp.get();
			operacao.getProdutos().forEach(t -> {
				Produto p = produtoRepository.findById(t.getId()).get();
				p.setQuantidade(p.getQuantidade() + t.getQuantidade());
				produtoRepository.saveAndFlush(p);
				
			});
			deletar(id);
		}

	}

}
