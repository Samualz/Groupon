package com.tortuousroad.framework.base.exception;

/**
 * <p>数据访问异常</p>
 */
public class DataAccessException extends BaseException {
	
	private static final long serialVersionUID = 4878102242940055883L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super("[R_DataAccessException]" + message, cause);
	}

	public DataAccessException(String message) {
		super("[R_DataAccessException]" + message);
	}

	public DataAccessException(Throwable cause) {
		super("[R_DataAccessException]", cause);
	}

}
