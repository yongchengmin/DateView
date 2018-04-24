package com.yc.view.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	public BusinessException(String message){
		super(message);
	}
}
