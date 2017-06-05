package com.briup.common.exception;

public class MusicServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public MusicServiceException() {
		super();
	}

	public MusicServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MusicServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MusicServiceException(String message) {
		super(message);
	}

	public MusicServiceException(Throwable cause) {
		super(cause);
	}
	
	
}
