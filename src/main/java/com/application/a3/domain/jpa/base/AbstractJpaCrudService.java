package com.application.a3.domain.jpa.base;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.domain.entity.Auditoria;
import com.application.a3.domain.entity.Operacao;
import com.application.a3.domain.jpa.repository.AuditoriaRepository;
import com.application.a3.exception.GenericException;
import com.application.a3.model.ref.AuditableEntity;
import com.application.a3.model.ref.CrudEntity;
import com.application.a3.model.ref.TipoOperacao;

import jakarta.persistence.EntityNotFoundException;

public abstract class AbstractJpaCrudService<D extends CrudEntity, M> {

	protected abstract <T extends JpaRepository<D, Integer>> T getRepository();
	protected abstract Class<D> getDomainClass();
	protected abstract Class<M> getModelClass();
	protected final AuditoriaRepository auditoriaRepository;
	protected ModelMapper modelMapper = new ModelMapper();
	
	public AbstractJpaCrudService(AuditoriaRepository auditoriaRepository) {
		this.auditoriaRepository = auditoriaRepository;
	}
	
	public M cadastrar(M dto) {
		D entity = mapperCadastro(dto, modelMapper);
		atualizarAntes(entity, dto);
		setCreateFields(entity);
		entity = getRepository().saveAndFlush(entity);
		
		if(entity instanceof AuditableEntity) {
			buildAuditoria(entity, TipoOperacao.CRIACAO);
		}
		
		atualizarDepois(entity, dto);
		return mappingResult(entity);

	}

	public M atualizar(int id, M dto) {
		isValido(dto);
		D entity = getRepository().getReferenceById(id);
		isAtivo(entity);
		mapperAtualizacao(dto, modelMapper, entity);
		entity = atualizarAntes(entity, dto);
		entity = getRepository().saveAndFlush(entity);	
		
		if(entity instanceof AuditableEntity) {
			buildAuditoria(entity, TipoOperacao.EDICAO);
		}
		
		atualizarDepois(entity, dto);
		return mappingResult(entity);
	}

	public void deletar(Integer id) {
		D entity = getRepository().getReferenceById(id);
		
		if(Objects.nonNull(entity)) {
			getRepository().deleteById(id);
		}
		
		if(entity instanceof Operacao) {
			buildAuditoria(entity, TipoOperacao.REEMBOLSO);
		} else if(entity instanceof AuditableEntity) {
			buildAuditoria(entity, TipoOperacao.EXCLUSAO);
		}
		

	
		
	}

	protected D mapperCadastro(M dto, ModelMapper mapper) {
		return mapper.map(dto, getDomainClass());
	}

	protected void mapperAtualizacao(M dto, ModelMapper mapper, D entity) {
		mapper.map(dto, entity);
	}

	protected M mappingResult(D entity) {
		return modelMapper.map(entity, getModelClass());
	}

	protected List<M> mappingResultList(List<D> entity) {
		List<M> temp = new ArrayList<M>();
		entity.forEach(t -> temp.add(mappingResult(t)));
		return temp;
	}

	protected void setCreateFields(D entity) {
		entity.setDataCriacao(Date.from(Instant.now()));
		entity.setAtivo(Boolean.TRUE);
	}

	protected D atualizarAntes(D entity, M dto) {
		return entity;
	}

	protected D atualizarDepois(D entity, M dto) {
		return entity;
	}

	protected Auditoria buildAuditoria(D entity, TipoOperacao operacao) {

		if (entity instanceof AuditableEntity) {
			Map<String, String> s = ((AuditableEntity) entity).getAuditoriaData();
			Auditoria auditoria = new Auditoria();
			auditoria.setTipoOperacao(operacao);
			auditoria.setDataAlteracao(Date.from(Instant.now()));
			auditoria.setNomeEntidade(s.get("nome"));
			auditoria.setIdEntidade(Integer.parseInt(s.get("id")));
			auditoria.setIdUsuario(0);
			auditoria.setUsuario("ADMIN");
			auditoria.setTipoEntidade(s.get("tipo"));
			auditoriaRepository.saveAndFlush(auditoria);
			return auditoria;

		}
		return null;
	}

	protected void isValido(Object obj) {
		if (obj == null) {
			throw new GenericException(ExceptionReturnMessage.ARGUMENTO_INVALIDO);
		}
	}

	protected void isAtivo(D entity) {
		if (!entity.getAtivo()) {
			throw new GenericException(ExceptionReturnMessage.REGISTRO_DESATIVADO);
		}
	}
	
}
