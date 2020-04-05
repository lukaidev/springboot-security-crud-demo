package com.example.exception;

import org.springframework.http.HttpStatus;

import com.example.constant.ErrorMessage;

public class DemoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMsg;
	private HttpStatus httpStatus;

	public DemoException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.httpStatus = HttpStatus.CONFLICT;
	}
	
	public DemoException(ErrorMessage errorMsg) {
		super(errorMsg.getValue());
		this.errorMsg = errorMsg.getValue();
		this.httpStatus = HttpStatus.CONFLICT;
	}

	public DemoException(String errorMsg, HttpStatus httpStatus) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.httpStatus = httpStatus;
	}
	
	public DemoException(ErrorMessage errorMsg, HttpStatus httpStatus) {
		super(errorMsg.getValue());
		this.errorMsg = errorMsg.getValue();
		this.httpStatus = httpStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	
}
