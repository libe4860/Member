package com.webapp.exception;

//insert시 중복멤버존재 Exception처리
public class MemberModifyEmptyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MemberModifyEmptyException() {
		super();
	}
	
	public MemberModifyEmptyException(String message){
		super(message);
	}
	
	public MemberModifyEmptyException(Throwable cause){
		super(cause);
	}
	
	public MemberModifyEmptyException(String message, Throwable cause){
		super(message, cause);
	}
}
