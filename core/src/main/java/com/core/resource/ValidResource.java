package com.core.resource;

import com.core.entities.User;
import com.core.repositories.UserRepository;
import com.core.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/valid")
public class ValidResource {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping(value = "/email/{token}/{user}")
	public ResponseEntity<HttpStatus> validUserEmail(
			@PathVariable UUID token,
			@PathVariable UUID user) {
		log.info("valid token {} user {}", token, user);
		this.loginService.validUserEmail(token, user);
		return ResponseEntity.ok().build();
	}
}
