package com.webapp.idgenerator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.webapp.mapper.IdGeneratorMapper;
import com.webapp.mapper.MemberMapper;
import com.webapp.model.Member;



public class GeneratorTableTest {

   static Log log = LogFactory.getLog(GeneratorTableTest.class);
   
   static GenericXmlApplicationContext ctx;
   
   
   
   public static void main(String[] args) throws SQLException {
      
	  //Spring하고는 관련없음
      ctx = new GenericXmlApplicationContext();
      //beans에 profile을 주면 활성화 시켜줘야 함
      ctx.getEnvironment().setActiveProfiles("mysql");
//      ctx.getEnvironment().setActiveProfiles("oracle");
      ctx.load("spring/beans_oracle.xml", "spring/beans_mysql.xml");
      ctx.refresh();
      //end of Spring하고는 관련없음
      
      for(int i=0; i<100; i++){
         Runnable run = new Runnable() {
            
            @Override
            public void run() {
               try {
//                  jdbc();
//                  jdbcTemplate();
//                  SqlSession();
                  SqlSessionTemplate();
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         };
         Thread t = new Thread(run);
         t.start();
      }
   }
   
   //트랜잭션을 시작하는 서비스 레이어
   static void SqlSessionTemplate() throws InterruptedException {
	   //스프링에서 등록되어있는 것들 가져오기
	   PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
	   
	   //트랜잭션 시작  정의
	   DefaultTransactionDefinition td = new DefaultTransactionDefinition();
	   td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	   td.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
	   
	   //트랜잭션 시작 
	   TransactionStatus status = tm.getTransaction(td);
	   //에러방지를 위한 롤백 처리
	   try{
		   memberInsert();
//		   memberUpdate();
		   tm.commit(status);
	   }catch(BadSqlGrammarException e){
		   log.info("BadSql..." + e.getMessage(), e);
	   }catch(Exception e){
		   tm.rollback(status);
		   e.printStackTrace();
	   }
	   
   }
   
   static void memberUpdate() throws InterruptedException {
	   Member member = new Member();
	   member.setId(14600);
	   member.setPassword("xxxx1234");
	   member.setName("yyyy");
	   MemberMapper memberMapper = ctx.getBean(MemberMapper.class);
	   
	   memberMapper.update(member);
   }
   
   static void memberInsert() throws InterruptedException {
	   Member member = ctx.getBean(Member.class);	   
	   IdGeneratorMapper idGenMapper = ctx.getBean(IdGeneratorMapper.class);
	   MemberMapper memberMapper = ctx.getBean("memberMapper", MemberMapper.class);
	   
	   //트랜잭션이 없는 상태에서의 기본 로직
	   Map<String, Object> idGen = idGenMapper.selectByName("memberId");
	   int nextval = ((BigDecimal)idGen.get("nextval")).intValue();
	   int incval = ((BigDecimal)idGen.get("incval")).intValue();
	   int seqno = nextval + incval;
	   idGen.put("nextval", seqno);
	   
	   Thread.sleep((int)(Math.random()*1000));
	   idGenMapper.update(idGen);
	   
	   member.setId(seqno);
	   member.setEmail(member.getEmail() + seqno);
	   memberMapper.insertGenerator(member);
	   log.info("seq = " + seqno);
	   
   }
   
   //SqlSession
   static void SqlSession() throws IOException, InterruptedException{
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis_config.xml");
		SqlSessionFactory factory = builder.build(inputStream, "mysql");
//		SqlSessionFactory factory = builder.build(inputStream, "oracle");
		
		SqlSession session = factory.openSession(false);
		
		IdGeneratorMapper idGeneratorMapper = session.getMapper(IdGeneratorMapper.class);
		MemberMapper memberMapper = session.getMapper(MemberMapper.class);
		
		Map<String, Object> idGen= idGeneratorMapper.selectByName("memberId");
		int nextval = ((BigDecimal)idGen.get("nextval")).intValue();
		int incval = ((BigDecimal)idGen.get("incval")).intValue();
		int seqno = nextval + incval;
		
		Thread.sleep((int)(Math.random()*3000));
		
		idGen.put("nextval", seqno);
		idGeneratorMapper.update(idGen);
		
		Member member = new Member();
		member.setId(seqno);
		member.setEmail("xxx@gen.com" + seqno);
		member.setPassword("1234");
		member.setName("홍길동");
		member.setRegdate(new Date());
		
		memberMapper.insertGenerator(member);
		
		log.info("seqno = " + seqno);
		
		session.commit();
		session.close();
   }
   
   //Mysql
   static void jdbcTemplate() throws SQLException, InterruptedException{
      
      JdbcTemplate template = ctx.getBean(JdbcTemplate.class);
      
      String sql = "select name, nextval, incval "   +
             " from id_generator "            +
             " where name = 'memberId'"         +
             " for update ";

      // ★★★ 트랜잭션 처리(동일번호 발급 방지 처리) - 명시적인 트랜잭션, 선언적 트랜잭션
//      DataSourceTransactionManager tm = new DataSourceTransactionManager();
//      tm.setDataSource(ctx.getBean(DataSource.class));
      
      PlatformTransactionManager tm = ctx.getBean(PlatformTransactionManager.class);
      
      //PROPAGATION_REQUIRES_NEW : 트랜잭션의 특성을 반영
      //ISOLATION_SERIALIZABLE : 트랜잭션을 동시가 아닌 순서대로 실행시키나 속도가 엄청 느림
      //ISOLATION_READ_COMMITTED : 트랜잭션을 동시에 수행
      //트랜잭션 정의
      DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
      definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
      definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
      definition.setReadOnly(false); //true : 업데이트 필요없을 때 사용, 속도가빨라짐 
//      definition.setTimeout(10);
      
      //트랜잭션 시작
      TransactionStatus status = tm.getTransaction(definition);
      
      //end of 트랜잭션 처리 
      
      Map<String, Object> gen = template.queryForMap(sql);
      
      Thread .sleep((int)(Math.random()*3000));
      
      String name = (String) gen.get("name");
      int nextval = ((BigDecimal) gen.get("nextval")).intValue();
      int incval = ((BigDecimal) gen.get("incval")).intValue();
      
      
      String update = "update id_generator      " +
                  " set nextval = ?         " +
                  " where name = 'memberId'   ";
      
      template.update(update, nextval + incval);
      
      //insert
      String insert = "insert into member2(id, email, password, name, regdate) " +
              		  "values ( ?, ?, 'xxx', 'yyy', '2015/08/13')";
      
      template.update(insert, nextval + incval, "xxx@"+ nextval);
      
      tm.commit(status);
      
      log.info("name = " + name+","+"nextval = " + (nextval+incval) +", incval = " + incval);
//      String insert = "insert into member(id, email, password, name, regdate) " +
//            "values ( ?, ?, 'xxx', 'yyy', sysdate)";
      
//      template.update(insert, seq, "xxx@" + seq);
   }
      
   //Oracle
   static void jdbc() throws SQLException{
      
      try {
         Thread.sleep((int)(Math.random()*3000));
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
//      BasicDataSource ds = ctx.getBean(BasicDataSource.class); 의존이 너무 많음
      //의존도를 많이 줄임
      DataSource ds = ctx.getBean(DataSource.class);
      log.info(((BasicDataSource) ds).getUrl());
      
      Connection con = ds.getConnection();
      con.setAutoCommit(false);
      
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
      String sql = "select name, nextval, incval "   +
                " from id_generator "            +
                " where name = 'memberId'"         +
                " for update ";
      ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      String name = rs.getString("name");
      int nextval = rs.getInt("nextval");
      int incval = rs.getInt("incval");
      int seq = nextval + incval;
      log.info("seq = " + seq);
      
      String insert = "insert into member2(id, email, password, name, regdate) " +
                  "values ( ?, ?, 'xxx', 'yyy', '2015/08/13')";
//                  "values ( ?, ?, 'xxx', 'yyy', sysdate)";
      //2. insert into member(id, email, password, name, regdate)
      //      values ( member_id_seq.nextval, 'wxxx1', 'ydyy1', 'zsz1', sysdate);
      PreparedStatement pstmt = con.prepareStatement(insert);
      pstmt.setInt(1, seq);
      pstmt.setString(2, "xxx@xxx.com" + seq);
      pstmt.executeUpdate();
      
      String update = "update id_generator      " +
                  " set nextval = ?         " +
                  " where name = 'memberId'   ";
      
      pstmt = con.prepareStatement(update);
      pstmt.setInt(1, seq);
      pstmt.executeUpdate();
      
      con.commit();
      con.close();
   }
}