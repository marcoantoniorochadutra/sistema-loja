package com.application.a3.web.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.model.dto.MessageDto;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<MessageDto> toResponse(EntityNotFoundException ex) {
		String msg = buildMessage(ex.getMessage());
		ResponseEntity<MessageDto> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(msg));
		return response;
	}

	private String buildMessage(String ex) {
	
			String id = ex.substring(ex.lastIndexOf(StringUtils.SPACE), ex.length());
			int entityStartOf = ex.indexOf("domain.entity.") + 14;
			String entity = ex.substring(entityStartOf, ex.indexOf(StringUtils.SPACE, entityStartOf));
			String errors = entity + ", " + id;
			return String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, errors);
		}

	
}
