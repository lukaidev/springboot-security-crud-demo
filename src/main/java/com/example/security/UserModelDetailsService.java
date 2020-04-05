package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.model.User;
import com.example.exception.UserNotActivatedException;
import com.example.repository.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

	private final UserRepository userRepository;

	public UserModelDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating user '{}'", login);


		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		return userRepository.findByUsername(lowercaseLogin).map(user -> createSpringSecurityUser(lowercaseLogin, user))
				.orElseThrow(() ->new UsernameNotFoundException("User " + lowercaseLogin + " not exists"));

	}

	private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin,
			User user) {
		if (!user.isActive()) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not active");
		}
		List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getRoleName())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
}