package com.defusername.bookworm.security.auth.dto;

public record SignInRequest(
		String username,
		String password) {

}
