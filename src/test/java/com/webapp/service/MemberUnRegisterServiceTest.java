package com.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.exception.AlreadyExistingMemberException;
import com.webapp.exception.MemberNotFoundException;
import com.webapp.exception.MemberUnRegisterEmptyException;
import com.webapp.model.Member;

public class MemberUnRegisterServiceTest {
	static Log log = LogFactory.getLog(MemberUnRegisterServiceTest.class);
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("oracle", "spring");
//		ctx.getEnvironment().setActiveProfiles("oracle", "mybatis");
//		ctx.getEnvironment().setActiveProfiles("mysql", "spring");
//		ctx.getEnvironment().setActiveProfiles("mysql", "mybatis");
		
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		MemberUnRegisterService service = ctx.getBean(MemberUnRegisterService.class);
		
		Member m = ctx.getBean(Member.class);
		m.setId(1000);
		m.setEmail(m.getEmail() + "xxx111");
		
		try{
//			service.unregister(m.getId());
			service.unregister(m.getEmail());
//			log.info("id = " + m.getId());
			log.info("id = " + m.getEmail());
		}catch(MemberUnRegisterEmptyException e){
			//에러처리
			log.info("삭제 대상 멤버가 없습니다.", e);
		}
		
	}

}
