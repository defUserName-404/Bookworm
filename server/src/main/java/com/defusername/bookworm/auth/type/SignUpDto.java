package com.defusername.bookworm.auth.type;

import com.defusername.bookworm.auth.user.UserRole;

public record SignUpDto(
		String username,
		String password,
		UserRole role) {

}
