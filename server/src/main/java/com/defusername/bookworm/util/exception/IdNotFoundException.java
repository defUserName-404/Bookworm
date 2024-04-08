package com.defusername.bookworm.util.exception;

public final class IdNotFoundException extends BookApiException {

	private IdNotFoundException(String message) {
		super(message);
	}

	public IdNotFoundException() {
		super("Id not found");
	}

}
