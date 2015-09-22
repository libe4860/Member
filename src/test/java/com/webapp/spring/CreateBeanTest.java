package com.webapp.spring;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;

public class CreateBeanTest {

	static Log log = LogFactory.getLog(CreateBeanTest.class);
	
	
	public static void main(String[] args) throws Exception {

		/* Object(객체)를 생성하는 방법 
		 * 1. new 객체생성
		 * 2. static method로 객체생성 newInstance() - 이유??? Singleton 설계 패턴을 실행하기 위해서
		 * ex) Calendar c = Calendar.getInstance();
		 * 3.FactoryBean으로 객체생성
		 * */
		String resourceLocations = "com/webapp/spring/createbean.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
//		CityDao dao = ctx.getBean(CityDao.class);
//		dao.print();
		
//		Member f = ctx.getBean(Member.class); //createbean.xml에 정의되어 있지 않아 에러남
		//static이나 new객체를 생성하지 않아도 getObject를 통해서 생성한 경우 createbean.xml에 정의되어 있지 않아도 에러발생하지 않음
		//정확한 정체는 SqlSessionTemplate이지만 SqlSession을 한번더 맵핑해서 mybatis와 spring을 서로 연계하는 역할)
		SqlSession session = ctx.getBean(SqlSession.class);
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		List<Member> list = mapper.selectAll();
		log.info("size = " + list.size());
		
		
		
//		MyFactoryBean fb = new MyFactoryBean();
//		SqlSessionFactory factory = fb.getObject();
		
		//싱글톤패턴 객체 생성방법
//		CityDao dao2 = CityDao.newInstance();
//		dao2.print();
		
	
		
		
		
		
	}

}
