package com.defusername.bookworm.security.auth.controller;

import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import com.defusername.bookworm.security.auth.entity.constant.UserRole;
import com.defusername.bookworm.security.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

	@GetMapping("/registration-form")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new SignUpRequest("", "", "", UserRole.USER));
		return "registration";
	}

	@GetMapping("/signin")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/signout")
	public String showSignoutForm() {
		return "logout";
	}

}
