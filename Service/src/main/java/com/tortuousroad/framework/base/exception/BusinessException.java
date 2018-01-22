package com.tortuousroad.framework.base.exception;

/**
 * <p>业务异常</p>
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = -4902153677077364421L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
	
}
