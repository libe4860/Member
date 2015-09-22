package com.webapp.exception;

//insert시 중복멤버존재 Exception처리
public class IdPasswordNotMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IdPasswordNotMatchException() {
		super();
	}
	
	public IdPasswordNotMatchException(String message){
		super(message);
	}
	
	public IdPasswordNotMatchException(Throwable cause){
		super(cause);
	}
	
	public IdPasswordNotMatchException(String message, Throwable cause){
		super(message, cause);
	}
}
