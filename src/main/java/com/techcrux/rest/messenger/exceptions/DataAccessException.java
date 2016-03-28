package com.techcrux.rest.messenger.exceptions;

/**
 * Exception to be thrown by the DAOs when something goes wrong with the database operations.
 * 
 * @author Nagendra.
 *
 */
public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2227152756019015214L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
