package com.tortuousroad.framework.base.exception;

/**
 * <p>框架异常</p>
 */
public class FrameworkException extends BaseException {
	
	private static final long serialVersionUID = 7014113898654462644L;

	public FrameworkException() {
		super();
	}

	public FrameworkException(String message, Throwable cause) {
		super("[R_DataAccessException]" + message, cause);
	}

	public FrameworkException(String message) {
		super("[R_DataAccessException]" + message);
	}

	public FrameworkException(Throwable cause) {
		super("[R_DataAccessException]", cause);
	}

}
