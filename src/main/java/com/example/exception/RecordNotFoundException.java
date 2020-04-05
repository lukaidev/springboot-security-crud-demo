package com.example.exception;

import org.springframework.http.HttpStatus;

import com.example.constant.ErrorMessage;

public class RecordNotFoundException extends DemoException{

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException() {
		super(ErrorMessage.RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

	public RecordNotFoundException(ErrorMessage errorMsg) {
		super(errorMsg, HttpStatus.NOT_FOUND);
	}

	public RecordNotFoundException(String errorMsg) {
		super(errorMsg, HttpStatus.NOT_FOUND);
	}

}
