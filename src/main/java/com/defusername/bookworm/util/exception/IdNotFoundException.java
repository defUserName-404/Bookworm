package com.defusername.bookworm.util.exception;

public final class IdNotFoundException extends ApiException {

	public IdNotFoundException() {
		super("Id not found");
	}

}
