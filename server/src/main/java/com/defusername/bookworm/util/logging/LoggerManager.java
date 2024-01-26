package com.defusername.bookworm.util.logging;

import org.slf4j.LoggerFactory;

public final class LoggerManager {

	private static LoggerManager INSTANCE;

	private LoggerManager() {

	}

	public static LoggerManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoggerManager();
		}

		return INSTANCE;
	}

	public org.slf4j.Logger getLogger(Class<?> className) {
		return LoggerFactory.getLogger(className);
	}

}
