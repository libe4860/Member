package com.webapp.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.ResourceUtils;

import com.webapp.model.Member;

public class MybatisTest {
   
   public static void main(String[] args) throws IOException {
      
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      
//      InputStream in = Resources.getUrlAsStream("file:mybatis_xxx.xml");    
//      MybatisTest.class.getClassLoader().getResourceAsStream("mybatis/mybatis_config.xml");
      //or
//      FileInputStream in = new FileInputStream("mybatis_xxx.xml");
      //or
      InputStream in = Resources.getResourceAsStream("mybatis/mybatis_config.xml");
      //or
//      File file = ResourceUtils.getFile("classpath:mybatis/mybatis_config.xml");
//      FileInputStream in = new FileInputStream(file);
      //or
//      File file = ResourceUtils.getFile("file:mybatis_xxx.xml");
//      FileInputStream in = new FileInputStream(file);
      
      SqlSessionFactory factory = builder.build(in);
      SqlSession session = factory.openSession();
      
      List<Member> list = session.selectList("com.webapp.dao.MemberMapper.selectAll");
      for(Member m : list)
         System.out.println(m.getId()       + " " + 
                        m.getEmail()      + " " + 
                        m.getName()       + " " + 
                        m.getPassword()    + " " + 
                        m.getRegdate());
      
      Member m = session.selectOne("com.webapp.dao.MemberMapper.selectById",1001);
         System.out.println(m.getId()       + " " + 
                  m.getEmail()      + " " + 
                  m.getName()       + " " + 
                  m.getPassword()    + " " + 
                  m.getRegdate());
   }
}