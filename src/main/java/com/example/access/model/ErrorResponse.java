package com.example.access.model;

import com.example.constant.ErrorMessage;

import lombok.Data;

@Data
public class ErrorResponse {

	private String error;

	public ErrorResponse() {
	}

	public ErrorResponse(String error) {
		this.error = error;
	}
	
	public ErrorResponse(ErrorMessage error) {
		this.error = error.getValue();
	}

}
