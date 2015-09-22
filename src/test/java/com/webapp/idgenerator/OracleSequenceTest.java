package com.webapp.idgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.webapp.dao.MemberDao;
import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;



public class OracleSequenceTest {

   static Log log = LogFactory.getLog(OracleSequenceTest.class);
   
   public static void main(String[] args) throws SQLException, IOException {
//      jdbc();
//      jdbcTemplate();
//      SqlSession();   //mybatis만 사용
      SqlSessionTemplate();   
      //최종 spring에서 mybatis를 사용
   }
   
   static void SqlSessionTemplate() throws IOException, SQLException{
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.getEnvironment().setActiveProfiles("oracle");
      ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
      ctx.refresh();
      
      DataSource ds = (DataSource) ctx.getBean("dataSource");
      
      //DB사양
      Connection con = ds.getConnection();
      log.info("db = " + con.getMetaData().getDatabaseProductName());
      log.info("ProductVersion = " + con.getMetaData().getDatabaseProductVersion());
      log.info("MajorVersion = " + con.getMetaData().getDatabaseMajorVersion());
      log.info("MinorVersion = " + con.getMetaData().getDatabaseMinorVersion());
      
      MemberMapper mapper = ctx.getBean(MemberMapper.class);
      
//      Member member = new Member();
//      member.setEmail("xxx@yyz.com" + (int)(Math.random()*100000));
//      member.setName("xxx");
//      member.setPassword("1234");
//      member.setRegdate(new Date());
      
      Member member = ctx.getBean(Member.class);
      
      member.setEmail(member.getEmail() + (int)(Math.random()*100000));
      
      log.info(member.getRegdate());
      
      mapper.insert(member);
      log.info("id = " + member.getId());
   }
   
   //mybatis만으로 사용
   static void SqlSession() throws IOException{
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();  //1회용 공장일뿐
      
      InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis_config.xml");
      SqlSessionFactory factory = builder.build(inputStream, "oracle");
//      SqlSessionFactory factory = builder.build(inputStream, "mysql");
      
      SqlSession session = factory.openSession(false);
      
      String statement = null;
      
      //ibatis 방식 CRUD => 매퍼파일(xml)
      Member member = session.selectOne("com.webapp.mapper.MemberMapper.selectById", 7);
      if(member != null){
         log.info("id = " + member.getId());
      } 
      List<Member> list = session.selectList("com.webapp.mapper.MemberMapper.selectAll");
      log.info("member size = " + list.size());
      
      member = new Member();
      member.setEmail("xxx@xxx.com" + (int)(Math.random() * 1000));
      member.setPassword("yyyy");
      member.setName("홍길동");
      member.setRegdate(new Date());
      session.insert("com.webapp.mapper.MemberMapper.insert", member);
//      session.delete("com.webapp.mapper.MemberMapper.delete", null);
//      session.update("com.webapp.mapper.MemberMapper.update", null);
      
      //Mapper Interface CRUD => Mapper Interface + 매퍼파일. Mybatis에서 하나가 추가됨
      //원래는 MemberMapper가 맞음
      MemberMapper mapper = session.getMapper(MemberMapper.class);
      list = mapper.selectAll();
      log.info("member size = " + list.size());
      member = mapper.selectById(1);
      if(member != null)
         log.info(member.getId());
//      
      member = new Member();
      member.setEmail("yyy@yyyy.com" + (int)(Math.random() * 1000));
      member.setPassword("yyy");
      member.setName("홍길동");
      member.setRegdate(new Date());
      
      mapper.insert(member);
//      dao.deleteByIdWithEmail(100, null);
//      dao.update(null);
      
      session.commit();
//      session.rollback();
   }
   
   //Mysql
   static void jdbcTemplate() throws SQLException{
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      //beans에 profile을 주면 활성화 시켜줘야 함
      ctx.getEnvironment().setActiveProfiles("oracle");
      ctx.load("classpath:spring/beans.xml");
      ctx.refresh();
      
      JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
      
      String sql = "select member_id_seq.nextval from dual";
      int seq = template.queryForObject(sql, Integer.class);
      log.info("seq = " + seq);
      
      String insert = "insert into member(id, email, password, name, regdate) " +
            "values ( ?, ?, 'xxx', 'yyy', sysdate)";
      
      template.update(insert, seq, "xxx@" + seq);
   }
      
   //Oracle
   static void jdbc() throws SQLException{
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      //beans에 profile을 주면 활성화 시켜줘야 함
      ctx.getEnvironment().setActiveProfiles("oracle");
      ctx.load("classpath:spring/beans.xml");
      ctx.refresh();
      
      BasicDataSource ds = ctx.getBean(BasicDataSource.class);
      log.info(ds.getUrl());
      
      Connection con = ds.getConnection();
      
      //1. select member_id_seq.nextval from dual;
      //1)
//      Statement stmt = con.createStatement();
//      
//      String insert = "insert into member(id, email, password, name, regdate) " +
//            "values ( member_id_seq.nextval, ?, 'xxx', 'yyy', sysdate)";
//      
//      PreparedStatement pstmt = con.prepareStatement(insert);
//      
//      pstmt.setString(1, "xxx@xxx.com1");
      
      //2)
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select member_id_seq.nextval from dual");
      rs.next();
      int seq = rs.getInt(1);
      log.info("seq = " + seq);
      
      String insert = "insert into member(id, email, password, name, regdate) " +
                  "values ( ?, ?, 'xxx', 'yyy', sysdate)";
      //2. insert into member(id, email, password, name, regdate)
      //      values ( member_id_seq.nextval, 'wxxx1', 'ydyy1', 'zsz1', sysdate);
      PreparedStatement pstmt = con.prepareStatement(insert);
      pstmt.setInt(1, seq);
      pstmt.setString(2, "xxx@xxx.com" + seq);
      pstmt.executeUpdate();
      
      con.close();
   }
}