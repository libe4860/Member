package com.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.exception.AlreadyExistingMemberException;
import com.webapp.exception.MemberModifyEmptyException;
import com.webapp.model.Member;

public class MemberModifyServiceTest {
	static Log log = LogFactory.getLog(MemberModifyServiceTest.class);
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("oracle", "spring");
//		ctx.getEnvironment().setActiveProfiles("oracle", "mybatis");
//		ctx.getEnvironment().setActiveProfiles("mysql", "spring");
//		ctx.getEnvironment().setActiveProfiles("mysql", "mybatis");
		
		ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
		ctx.refresh();
		
		MemberModifyService service = ctx.getBean(MemberModifyService.class);
		MemberInfoService infoService = ctx.getBean(MemberInfoService.class);
		
		Member m = ctx.getBean(Member.class);
		m.setId(101000); //멤버에서 없는 경우 익셉션 발생
		m.setEmail("xxx@oracle.comxxx");
		
		m.setPassword("qwer1234");
		m.setName("xxx");
		
		try{
			service.modify(m);
			log.info("id = " + m.getId());
		}catch(MemberModifyEmptyException e){
			//에러처리
			log.info("멤버수정에러", e);
		}
		
	}

}
