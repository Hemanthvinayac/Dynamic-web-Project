package com.brillio.training.dao;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	// this constructor helps creating a
	// DaoException instance using another exception
	public DaoException(Throwable cause) {
		super(cause);
	}

}
