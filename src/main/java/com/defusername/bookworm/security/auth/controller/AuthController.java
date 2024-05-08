package com.defusername.bookworm.security.auth.controller;

import com.defusername.bookworm.security.auth.dto.SignInRequest;
import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import com.defusername.bookworm.security.auth.entity.User;
import com.defusername.bookworm.security.auth.entity.constant.UserRole;
import com.defusername.bookworm.security.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("user") SignUpRequest userData) {
		final SignUpRequest newSignUpRequest = new SignUpRequest(
				userData.username(),
				userData.email(),
				userData.password(),
				UserRole.USER
		);
		authService.signUp(newSignUpRequest);
		return "redirect:/auth/login";
	}

	@GetMapping("/signin")
	public ResponseEntity<User> login(SignInRequest userData) {
		final User user = authService.signIn(userData);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(user);
	}

}
