package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.model.User;
import com.example.repository.UserRepository;
import com.example.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackOn = { Exception.class })
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Optional<User> getCurrentUser() {
		return SecurityUtils.getCurrentUsername().flatMap(userRepository::findByUsername);
	}

	public User save(User user) {
		return userRepository.save(User.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword()))
				.roles(user.getRoles())
				.active(user.isActive())
				.build());
	}
	
	public List<User> saveAll(List<User> users) {
		return userRepository.saveAll(users.stream().map(s -> User.builder()
				.id(s.getId())
				.username(s.getUsername())
				.password(passwordEncoder.encode(s.getPassword()))
				.roles(s.getRoles())
				.active(s.isActive())
				.build()).collect(Collectors.toList()));
	}

	public User getByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User " + username + " not exists"));
	}

	public long countAllUser() {
		return userRepository.count();
	}

}
