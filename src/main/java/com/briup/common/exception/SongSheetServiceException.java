package com.briup.common.exception;

public class SongSheetServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	public SongSheetServiceException() {
		super();
	}

	public SongSheetServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SongSheetServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SongSheetServiceException(String message) {
		super(message);
	}

	public SongSheetServiceException(Throwable cause) {
		super(cause);
	}
	
}
