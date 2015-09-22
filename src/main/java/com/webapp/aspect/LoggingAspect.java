package com.webapp.aspect;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

//Aspect처리
@Aspect
@Order(0)
public class LoggingAspect {
	//pointcut대상설정                  모든패키지          모든설정/모든 파라미터
//	@Pointcut("execution(public * *..*MemberDao.*(..) )")
	@Pointcut("execution(public * com.webapp.dao.*MemberDao.*(..)) "
			+ "||"
			+ "execution(public * com.webapp.service.*Service.*(..))")
	void pointcut(){
		
	}
	
	@Pointcut("execution(public * com.webapp.service.*Service.*(..) )")
	void service(){
		
	}
	
	@Before("pointcut()")
	void before(JoinPoint jp){
		//getTarget() real객체
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		String msg = "###" + 
					 jp.getSignature().getName() + 
					 "(" + 
					 Arrays.toString(jp.getArgs()) +
					 ")" +
					 "START";
		log.info(msg);
	}
	
	@After("pointcut()")
	void after(JoinPoint jp){
		Log log = LogFactory.getLog(jp.getTarget().getClass());
		String msg = "###" + 
				 jp.getSignature().getName() + 
				 "(" + 
				 Arrays.toString(jp.getArgs()) +
				 ")" +
				 "END";
		log.info(msg);
	}

}
