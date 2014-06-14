package net.tavilog.exception;

public class MapInitException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MapInitException(String message) {
		super(message);
	}

	public MapInitException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
