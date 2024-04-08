package com.defusername.bookworm.util.exception;

public sealed class BookApiException extends Exception permits IdNotFoundException {

	final String message;

	BookApiException(String message) {
		this.message = message;
	}

}

