package com.example.constant;

public enum ErrorMessage {

	SOMETHING_WENT_WRONG("Something went wrong"),
	RECORD_NOT_FOUND("Not found."),
	INVALID_CREDENTIALS("Invalid credentials"),
	INVALID_PAYLOAD_FORMAT("Invalid payload format");

	private String value;

	ErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
