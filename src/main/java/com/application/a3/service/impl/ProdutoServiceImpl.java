package com.application.a3.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.domain.entity.Fornecedor;
import com.application.a3.domain.entity.Produto;
import com.application.a3.domain.jpa.base.AbstractJpaCrudService;
import com.application.a3.domain.jpa.repository.AuditoriaRepository;
import com.application.a3.domain.jpa.repository.FornecedorRepository;
import com.application.a3.domain.jpa.repository.ProdutoRepository;
import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.ProdutoDto;
import com.application.a3.service.ProdutoService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ProdutoServiceImpl extends AbstractJpaCrudService<Produto, ProdutoDto> implements ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final FornecedorRepository fornecedorRepository;
	
	@Autowired
	public ProdutoServiceImpl(
			AuditoriaRepository auditoriaRepository,
			ProdutoRepository produtoRepository, 
			FornecedorRepository fornecedorRepository) {
		super(auditoriaRepository);
		this.produtoRepository = produtoRepository;
		this.fornecedorRepository = fornecedorRepository;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected ProdutoRepository getRepository() {
		return produtoRepository;
	}

	@Override
	protected Class<Produto> getDomainClass() {
		return Produto.class;
	}

	@Override
	protected Class<ProdutoDto> getModelClass() {
		return ProdutoDto.class;
	}

	public ProdutoDto cadastrarProduto(ProdutoDto dto) {
		if (produtoRepository.checarExistencia(dto.getFornecedorCadastroNacional(), dto.getNome()).size() != 0) {
			throw new GenericException(ExceptionReturnMessage.DADO_DUPLICADO);
		} else {
			return cadastrar(dto);
		}

	}

	@Override
	public ProdutoDto atualizarProduto(Integer id, ProdutoDto dto) {
		ProdutoDto produto = atualizar(id, dto);
		if (Objects.nonNull(produto)) {
			produto.setFornecedorCadastroNacional(dto.getFornecedorCadastroNacional());
			return produto;
		}
		return null;
	}

	@Override
	protected Produto atualizarAntes(Produto entity, ProdutoDto dto) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findByCadastroNacional(dto.getFornecedorCadastroNacional());
		if (fornecedor.isEmpty()) {
			throw new GenericException(String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, "Fornecedor: " + dto.getFornecedorCadastroNacional()));
		} else {
			entity.setFornecedor(fornecedor.get());
			return entity;
		}

	}


	@Override
	public Produto buscarProduto(Integer id) {
		Optional<Produto> s = getRepository().findById(id);
		if (s.isEmpty()) {
			throw new GenericException(String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, id));
		} else {
			return s.get();
		}
	}

	@Override
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	@Override
	public void excluirProduto(Integer id) {
		if (getRepository().existsById(id)) {
			getRepository().deleteById(id);
		} else {
			throw new EntityNotFoundException("Produto, " + id);
		}

	}

}
