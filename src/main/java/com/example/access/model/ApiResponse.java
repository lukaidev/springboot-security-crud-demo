package com.example.access.model;

import java.util.Date;

import com.example.constant.ResponseMessage;

import lombok.Data;

@Data
public class ApiResponse {

	private boolean success;
	private Date timestamp;
	private String message;
	private Object data;

	public static ApiResponse success(Object data) {
		return new ApiResponse(true, new Date(), null, data);
	}

	public static ApiResponse success(Object data, String message) {
		return new ApiResponse(true, new Date(), message, data);
	}

	public static ApiResponse success(Object data, ResponseMessage rm) {
		return new ApiResponse(true, new Date(), rm.getValue(), data);
	}

	public static ApiResponse failed(Object data) {
		return new ApiResponse(false, new Date(), null, data);
	}

	public static ApiResponse failed(Object data, String message) {
		return new ApiResponse(false, new Date(), message, data);
	}

	public static ApiResponse failed(Object data, ResponseMessage rm) {
		return new ApiResponse(false, new Date(), rm.getValue(), data);
	}

	public ApiResponse(boolean success, Date timestamp, String message, Object data) {
		this.success = success;
		this.timestamp = timestamp;
		this.message = message;
		this.data = data;
	}

}
