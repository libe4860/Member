package com.webapp.spring;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;

//SqlSessionFactory - builder와 같은 역할을 함
public class MyFactoryBean implements FactoryBean<SqlSessionFactory>{

	//싱글톤패턴
	static SqlSessionFactory factory;
	@Override
	public SqlSessionFactory getObject() throws Exception {
		//싱글톤으로 만들어 내야함.
		if(factory == null){
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(Resources.getResourceAsStream("mybatis/mybatis_config.xml"), "oracle");
		}
		
		return factory;
	}

	
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		//어떤걸 만들어내는지 알려줌
		return SqlSessionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
