package com.excilys.computerDatabase.model.exception;

@SuppressWarnings("serial")
public class DateException extends Exception {

	public DateException() {
	}

	public DateException(String message) {
		super(message);
	}

	public DateException(Throwable cause) {
		super(cause);
	}

	public DateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}