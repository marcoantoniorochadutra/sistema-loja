package com.application.a3.web.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.model.dto.MessageDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {
	
	@ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseEntity<MessageDto> toResponse(ConstraintViolationException ex) {
		String msg = buildMessage(ex.getConstraintViolations());
		ResponseEntity<MessageDto> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(msg));
		return response;
	}

	private String buildMessage(Set<ConstraintViolation<?>> violations) {

		StringBuffer sb = new StringBuffer();

		List<String> nullFields = new ArrayList<String>();

		List<String> patternFields = new ArrayList<String>();

		List<String> sizeFields = new ArrayList<String>();

		List<String> numberFields = new ArrayList<String>();

		for (ConstraintViolation<?> violation : violations) {
			if (!matchNullAnnotation(violation, nullFields) && !matchPatternAnnotation(violation, patternFields)
					&& !matchSizeAnnotaion(violation, sizeFields) && !matchNumberAnnotation(violation, numberFields)) {
				sb.append(String.format(""));
			}
		}
		if (!nullFields.isEmpty()) {
			sb.append(String.format(" %s  %s.", ExceptionReturnMessage.INFORMACAO_NULA, nullFields.toString()));
		}
		if (!patternFields.isEmpty()) {
			sb.append(String.format(" %s  %s.", ExceptionReturnMessage.TAMANHO_INVALIDO, patternFields.toString()));
		}
		if (!sizeFields.isEmpty()) {
			sb.append(String.format(" %s  %s.", ExceptionReturnMessage.TAMANHO_INVALIDO, sizeFields.toString()));
		}
		if (!numberFields.isEmpty()) {
			sb.append(String.format(" %s  %s.", ExceptionReturnMessage.ARGUMENTO_INVALIDO, numberFields.toString()));
		}

		return sb.toString();
	}

	private boolean matchNullAnnotation(ConstraintViolation<?> violation, List<String> nullFields) {
		boolean isNullAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof NotNull || violation.getConstraintDescriptor().getAnnotation() instanceof NotBlank) {
			nullFields.add(violation.getPropertyPath().toString());
			isNullAnnotation = true;
		}
		return isNullAnnotation;
	}

	private boolean matchPatternAnnotation(ConstraintViolation<?> violation, List<String> patternFields) {
		boolean isPatternAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Pattern) {
			patternFields.add(violation.getPropertyPath().toString());
			isPatternAnnotation = true;
		}
		return isPatternAnnotation;
	}

	private boolean matchSizeAnnotaion(ConstraintViolation<?> violation, List<String> sizeFields) {
		boolean isSizeAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Size) {
			sizeFields.add(violation.getPropertyPath().toString());
			isSizeAnnotation = true;
		}
		return isSizeAnnotation;
	}

	private boolean matchNumberAnnotation(ConstraintViolation<?> violation, List<String> numberFields) {
		boolean isSizeAnnotation = false;
		if (violation.getConstraintDescriptor().getAnnotation() instanceof Min
				|| violation.getConstraintDescriptor().getAnnotation() instanceof Max) {
			numberFields.add(violation.getPropertyPath().toString());
			isSizeAnnotation = true;
		}
		return isSizeAnnotation;
	}
}
