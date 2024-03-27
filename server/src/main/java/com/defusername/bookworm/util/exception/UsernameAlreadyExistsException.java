package com.defusername.bookworm.util.exception;

import org.springframework.security.core.AuthenticationException;

public final class UsernameAlreadyExistsException extends AuthenticationException {

	public UsernameAlreadyExistsException() {
		super("Username already exists");
	}

}
