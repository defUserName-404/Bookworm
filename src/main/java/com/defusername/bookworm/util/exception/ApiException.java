package com.defusername.bookworm.util.exception;

public sealed class ApiException extends Exception permits IdNotFoundException {

	final String message;

	ApiException(String message) {
		this.message = message;
	}

}

