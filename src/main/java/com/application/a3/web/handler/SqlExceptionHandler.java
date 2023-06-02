package com.application.a3.web.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.a3.constants.ExceptionReturnMessage;
import com.application.a3.model.dto.MessageDto;

@ControllerAdvice
public class SqlExceptionHandler {
	
	private final static Logger log = LoggerFactory.getLogger(SqlExceptionHandler.class);

	public static final int DB_DUPLICATE_KEY = 1062;
	public static final int DB_PARENT_KEY = 1451;
	public static final int DB_PARENT_NOT_FOUND = 1452;
	public static final int DB_FIELD_NO_NULL = 1048;
	public static final int DB_DATA_TOO_LONG = 1406;
	public static final int DB_FOREIGN_KEY_FAIL = 1216;

	@ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
	public ResponseEntity<MessageDto> toResponse(ConstraintViolationException ex) {
		String msg = transformDBException(ex.getErrorCode(), ExceptionUtils.getRootCauseMessage(ex));
		ResponseEntity<MessageDto> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(msg));
		return response;
	}
	
	private String transformDBException(int sqlErrorCode, String sqlErrorMsg) {
		System.out.println(sqlErrorCode);
		switch (sqlErrorCode) {
		case DB_DUPLICATE_KEY:
			return getDuplicatedFieldMessage(sqlErrorMsg);
		case DB_PARENT_KEY:
			return getParentFieldMessage(sqlErrorMsg);
		case DB_FIELD_NO_NULL:
			return getFieldNotNullMessage(sqlErrorMsg);
		case DB_DATA_TOO_LONG:
			return getDataTooLongMessage(sqlErrorMsg);
		case DB_FOREIGN_KEY_FAIL:
			return getForeignKeyMessage(sqlErrorMsg);
		case DB_PARENT_NOT_FOUND:
			return getParentNotFoundFieldMessage(sqlErrorMsg);
		default:
			log.warn("SQL error code not mapped {} {}", sqlErrorCode, sqlErrorMsg);
			return null;
		}

	}

	private String getDataTooLongMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("' at");
		String fieldName = StringUtils.capitalize(keyName.substring(0, endIndex));
		return String.format("%s [ %s ].", ExceptionReturnMessage.TAMANHO_INVALIDO, fieldName);
	}

	private String getFieldNotNullMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("Column '") + 8;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("'");
		String fieldName = StringUtils.capitalize(keyName.substring(0, endIndex));
		return String.format("%s [ %s ].", ExceptionReturnMessage.INFORMACAO_NULA, fieldName);
	}

	private String getDuplicatedFieldMessage(String sqlMsg) {
		int startIndex = sqlMsg.indexOf("entry '") + 7;
		String keyName = sqlMsg.substring(startIndex, sqlMsg.length() - 1);
		int endIndex = keyName.indexOf("'");
		String fieldValue = StringUtils.capitalize(keyName.substring(0, endIndex));
		return String.format("%s [ %s ].", ExceptionReturnMessage.DADO_DUPLICADO, fieldValue);
	}
	
	private String getParentNotFoundFieldMessage(String sqlMsg) {
		int firstIndex = sqlMsg.indexOf("REFERENCES");
		String keyName = sqlMsg.substring(firstIndex, sqlMsg.length() - 1);
		int startIndex = keyName.indexOf("`");
		int endIndex = keyName.indexOf("` (");
		String fieldValue = StringUtils.capitalize(keyName.substring(startIndex + 1, endIndex));
		
		return String.format(ExceptionReturnMessage.REGISTRO_NAO_ENCONTRADO, fieldValue, "");
	}

	private String getForeignKeyMessage(String sqlMsg) {
		return ExceptionReturnMessage.REFERENCIA_INEXISTENTE;
	}

	private String getParentFieldMessage(String sqlMsg) {
		return "Registro possui outros registros vinculados";
	}
}
