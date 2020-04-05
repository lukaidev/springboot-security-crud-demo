package com.example.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.model.Role;
import com.example.entity.model.User;
import com.example.service.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Transactional
public class AppStartupRunner implements ApplicationRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.checkAndCreateIfNoUserFound();
	}

	private void checkAndCreateIfNoUserFound() {
		log.info("Checking if users exists.");
		if (userService.countAllUser() == 0) {

			log.info("Creating default users.");

			Set<Role> adminRole = new HashSet<>();
			adminRole.add(Role.builder().roleName("ADMIN").description("Admin role").active(true).build());

			Set<Role> userRole = new HashSet<>();
			userRole.add(Role.builder().roleName("USER").description("User role").active(true).build());

			List<User> defaultUsers = new ArrayList<>();
			defaultUsers.add(User.builder().username("admin").password("admin").roles(adminRole).active(true).build());
			defaultUsers.add(User.builder().username("user").password("user").roles(userRole).active(true).build());
			
			userService.saveAll(defaultUsers);
			log.info("default users created.");
		} else {
			log.info("Users exists");
		}
	}
}
