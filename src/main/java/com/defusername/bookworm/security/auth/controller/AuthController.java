package com.defusername.bookworm.security.auth.controller;

import com.defusername.bookworm.security.auth.service.AuthService;
import com.defusername.bookworm.security.auth.dto.SignInRequest;
import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody SignUpRequest data) {
		authService.signUp(data);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .build();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody SignInRequest data) {
		try {
			authService.signIn(data);
			return ResponseEntity.status(HttpStatus.OK)
								 .build();
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .build();
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								 .build();
		}
	}

}

