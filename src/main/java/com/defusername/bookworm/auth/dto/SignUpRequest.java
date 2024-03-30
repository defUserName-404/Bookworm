package com.defusername.bookworm.auth.dto;

import com.defusername.bookworm.auth.entity.constant.UserRole;

public record SignUpRequest(
		String username,
		String email,
		String password,
		UserRole role) {

}
