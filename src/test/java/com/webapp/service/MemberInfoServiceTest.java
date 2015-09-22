package com.webapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.webapp.exception.MemberNotFoundException;
import com.webapp.model.Member;

public class MemberInfoServiceTest {
   static Log log = LogFactory.getLog(MemberInfoServiceTest.class);
   public static void main(String[] args) {
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//      ctx.getEnvironment().setActiveProfiles("oracle", "spring");
//      ctx.getEnvironment().setActiveProfiles("oracle", "mybatis");
//      ctx.getEnvironment().setActiveProfiles("mysql", "spring");
      ctx.getEnvironment().setActiveProfiles("mysql", "mybatis");
      
      ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
      ctx.refresh();
      
      MemberInfoService service = ctx.getBean(MemberInfoService.class);
      
      try{
//         Member m = service.getMember(0);      //id에러
         Member m = service.getMember("xxxxx");   //email에러
//         log.info("id = " + m.getId());
         log.info("email = " + m.getEmail());
      }catch(MemberNotFoundException e){
//         log.info("id = " + m.getId());
//         log.info("로그인 아이디가 없습니다.", e);
         log.info("이메일양식이 맞지 않습니다.", e);
      }
      
   }

}