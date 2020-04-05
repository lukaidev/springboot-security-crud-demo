package com.example.access.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PageResponse {
	
	private Integer pages;
	private Long elements;
	private Object results;

}
