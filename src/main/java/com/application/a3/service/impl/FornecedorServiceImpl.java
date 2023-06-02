package com.application.a3.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.domain.entity.Fornecedor;
import com.application.a3.domain.jpa.base.AbstractJpaCrudService;
import com.application.a3.domain.jpa.repository.AuditoriaRepository;
import com.application.a3.domain.jpa.repository.FornecedorRepository;
import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.FornecedorDto;
import com.application.a3.service.FornecedorService;
import com.application.a3.utils.CadastroNacionalHelper;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class FornecedorServiceImpl extends AbstractJpaCrudService<Fornecedor, FornecedorDto>
		implements FornecedorService {

	private final FornecedorRepository fornecedorRepository;

	@Autowired
	public FornecedorServiceImpl(
			AuditoriaRepository auditoriaRepository,
			FornecedorRepository fornecedorRepository) {
		super(auditoriaRepository);
		this.fornecedorRepository = fornecedorRepository;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected FornecedorRepository getRepository() {
		return fornecedorRepository;
	}

	@Override
	protected Class<Fornecedor> getDomainClass() {
		return Fornecedor.class;
	}

	@Override
	protected Class<FornecedorDto> getModelClass() {
		return FornecedorDto.class;
	}

	@Override
	public FornecedorDto cadastrarFornecedor(FornecedorDto dto) {
		CadastroNacionalHelper.validarCadastroNacional(dto);
		return cadastrar(dto);
	}


	@Override
	public FornecedorDto atualizarFornecedor(Integer id, FornecedorDto dto) {
		CadastroNacionalHelper.validarCadastroNacional(dto);
		return atualizar(id, dto);
	}

	@Override
	public Fornecedor buscarFornecedor(String cadastroNacional) {
		Optional<Fornecedor> s = getRepository().findByCadastroNacional(cadastroNacional);
		if (s.isEmpty()) {
			throw new GenericException(String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, cadastroNacional));
		} else {
			return s.get();

		}
	}

	@Override
	public void excluirFornecedor(Integer id) {
		deletar(id);
	}

	@Override
	public List<Fornecedor> listarFornecedor() {
		return fornecedorRepository.findAll();
	}



}
