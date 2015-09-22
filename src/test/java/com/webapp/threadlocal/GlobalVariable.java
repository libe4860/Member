package com.webapp.threadlocal;

public class GlobalVariable {
	//모든클래스에서 접근가능 (전역변수)
	public static ThreadLocal<Result2> result = new ThreadLocal<Result2>();
//	public static int sum;
	

}
