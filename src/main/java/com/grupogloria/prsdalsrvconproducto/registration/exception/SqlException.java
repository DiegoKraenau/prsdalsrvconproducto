package com.grupogloria.prsdalsrvconproducto.registration.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SqlException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public SqlException(String exception) {
	    super(exception);
	  }
}
