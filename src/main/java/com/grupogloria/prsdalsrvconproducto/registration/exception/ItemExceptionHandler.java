package com.grupogloria.prsdalsrvconproducto.registration.exception;

import java.util.Date;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkLogger;
import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkPayload;
import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;

@ControllerAdvice
public class ItemExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(ItemNotFoundException.class)
	@LogMethodCall
	public ResponseEntity<Object> handleUserNotFoundException(ItemNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
		        request.getDescription(false));		
		ElkLogger.log(Level.ERROR,new ElkPayload(GlobalConstants.ExceptionOccured,HttpStatus.NOT_FOUND.toString(),exception.getMessage()),this.getClass().getName(), exception);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SqlException.class)
	@LogMethodCall
	public ResponseEntity<Object> dataBaseException(SqlException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
		        request.getDescription(false));		
		ElkLogger.log(Level.ERROR,new ElkPayload(GlobalConstants.ExceptionOccured,HttpStatus.INTERNAL_SERVER_ERROR.toString(),exception.getMessage()),this.getClass().getName(), exception);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@LogMethodCall
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> haldleAllException(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
		        request.getDescription(false));
		ElkLogger.log(Level.ERROR,new ElkPayload(GlobalConstants.ExceptionOccured,HttpStatus.NOT_FOUND.toString(),ElkLogger.getStackTrace(exception)),this.getClass().getName(), exception);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	@Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, 
    		HttpHeaders headers, HttpStatus status, WebRequest request) { 
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
		        request.getDescription(false));		
		ElkLogger.log(Level.ERROR,new ElkPayload(GlobalConstants.ExceptionOccured,HttpStatus.BAD_REQUEST.toString(),exception.getMessage()),this.getClass().getName(), exception);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); 
	}
	
	@LogMethodCall
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> exception(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
		        request.getDescription(false));
		ElkLogger.log(Level.ERROR,new ElkPayload(GlobalConstants.ExceptionOccured,HttpStatus.INTERNAL_SERVER_ERROR.toString(),exception.getMessage()),this.getClass().getName(), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
