package com.example.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.access.model.ErrorResponse;
import com.example.constant.ErrorMessage;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exception(Exception e) {
		HttpStatus status = HttpStatus.CONFLICT;
		log.error(e.getLocalizedMessage(), e);
		return new ResponseEntity<>(new ErrorResponse(ErrorMessage.SOMETHING_WENT_WRONG), status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> exception(MethodArgumentNotValidException e) {
		String msg = e.getBindingResult().getFieldError().getDefaultMessage();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(new ErrorResponse(msg), status);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(EntityNotFoundException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		log.error(e.getLocalizedMessage(), e);
		return new ResponseEntity<>(new ErrorResponse(ErrorMessage.RECORD_NOT_FOUND), status);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> exception(BadCredentialsException e) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		log.error(e.getLocalizedMessage(), e);
		return new ResponseEntity<>(new ErrorResponse(ErrorMessage.INVALID_CREDENTIALS), status);
	}


	@ExceptionHandler(DemoException.class)
	public ResponseEntity<ErrorResponse> exception(DemoException e) {
		String msg = e.getErrorMsg();
		HttpStatus status = e.getHttpStatus();
		return new ResponseEntity<>(new ErrorResponse(msg), status);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> exception(RecordNotFoundException e) {
		String msg = e.getErrorMsg();
		HttpStatus status = e.getHttpStatus();
		return new ResponseEntity<>(new ErrorResponse(msg), status);
	}
}
