
package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.access.model.LoginDto;
import com.example.access.model.LoginResponseDto;
import com.example.access.model.LoginResponseDto.LoginResponseDtoBuilder;
import com.example.entity.model.Role;
import com.example.security.jwt.JWTFilter;
import com.example.security.jwt.TokenProvider;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationRestController {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserService userService;

	@PostMapping
	public ResponseEntity<LoginResponseDto> authorize(@Valid @RequestBody LoginDto loginDto) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		boolean rememberMe = (loginDto.getRememberMe() == null) ? false : loginDto.getRememberMe();
		String jwt = tokenProvider.createToken(authentication, rememberMe);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		LoginResponseDtoBuilder lr = LoginResponseDto.builder();
		
		 userService.getCurrentUser().ifPresent(s -> lr.token(jwt)
				 .username(s.getUsername())
				 .roles(s.getRoles().stream().map(AuthenticationRestController::roleValue)
						 .collect(Collectors.toList())));

		return new ResponseEntity<>(lr.build(), httpHeaders, HttpStatus.OK);
	}

	private static String roleValue(Role role) {
		return role.getRoleName();
	}

}
