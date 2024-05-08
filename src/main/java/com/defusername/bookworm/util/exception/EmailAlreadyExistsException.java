package com.defusername.bookworm.util.exception;

import org.springframework.security.core.AuthenticationException;

public final class EmailAlreadyExistsException extends AuthenticationException {

	public EmailAlreadyExistsException() {
		super("Email already exists");
	}

}
