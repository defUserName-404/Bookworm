package com.defusername.bookworm.security.auth.dto;

import com.defusername.bookworm.security.auth.entity.constant.UserRole;

public record SignUpRequest(
		String username,
		String email,
		String password,
		UserRole role) {

}
