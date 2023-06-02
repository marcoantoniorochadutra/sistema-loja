package com.application.a3.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.a3.exception.GenericException;
import com.application.a3.model.dto.MessageDto;

@ControllerAdvice
public class GenericExceptionHandler {

	@ExceptionHandler(value=GenericException.class)
	public ResponseEntity<MessageDto> toResponse(Exception ex) {
		ResponseEntity<MessageDto> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(ex.getMessage()));
		return response;
	}
}
