package com.example.access.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class LoginResponseDto {

	private String token;
	private String username;
	private List<String> roles;

}
