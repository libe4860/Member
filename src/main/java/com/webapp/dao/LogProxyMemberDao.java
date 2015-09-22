package com.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.webapp.model.Member;

public class LogProxyMemberDao implements MemberDao {
	
	static Log log;
	
	//진짜 MemberDao
	MemberDao real;
	

	public LogProxyMemberDao(MemberDao real) {
		this.real = real;
		//로그만들기
		log = LogFactory.getLog(real.getClass());
	}
	
	@Override
	public List<Member> selectAll() { 
		log.info("########################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectAll() start..."	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################"); //cross - cutting concern 
		//BeforeAdvice : 메서드 호출전에 삽입
		//real객체 호출하여 시작과 끝에 기능추가
		List<Member> list= real.selectAll(); //JoinPoint(조인포인트) : 어드바이스를 삽입할 수 있는 위치(필드,메서드라면 모두 삽입가능)
		//단 스프링은 필드쪽은 지원하지 않는다! 
		log.info("########################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectAll() end..."	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################"); //cross - cutting concern
		//AfterAdvice : 메서드 호출 후에 삽입(에러든 성공이든 모두 수행)
		//AfterReturning : 성공시에만 삽입
		//AfterThrowing : 에러시에만 삽입
		
		//AroundAdvice(모두합친것) : BeforeAdvice + AfterAdvice + AfterReturning + AfterThrowing
		
		//Pointcut : weaving되는 JoinPoint 지점을 모아 놓은 것
		
		//Aspect(클래스) = Pointcut + Advice(after+before모두 합쳐놓은것)
		return list;
	}

	@Override
	public List<Member> select(Map<String, Object> index) { 
		log.info("##################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("select(Map<String,Object> start..."	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("##################################"); //cross - cutting concern
		//BeforeAdvice
		List<Member> list = real.select(index); //JoinPoint(조인포인트)
		log.info("################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("select(Map<String,Object> end..."	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("################################"); //cross - cutting concern 
		//AfterAdvice
		return list;
	}

	@Override
	public Member selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectTotalCount() {
		int rtn = 0;
	try{
		log.info("########################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectTotalCount() start... BeforeAdvice"	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################################"); //cross - cutting concern
		//BeforeAdvice
		rtn = real.selectTotalCount(); //JoinPoint(조인포인트)
		log.info("########################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectTotalCount() AfterReturning"	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################################"); //cross - cutting concern
		//AfterReturing
	}catch(Throwable t){
		log.info("########################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectTotalCount() AfterThrowing"	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################################"); //cross - cutting concern
		//AfterThrowing
	}finally{
		log.info("########################################"); //횡단적 기능, 횡단적 관심(관점) = Aspect
		log.info("selectTotalCount() end... AfterAdvice"	   ); //횡단적 관심사의 분리 = cross cutting ==> AOP의 역할(하나의 클래스로 만들어준다) 
		log.info("########################################"); //cross - cutting concern
		//AfterAdvice
		
	}

		return rtn;
	}

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertGenerator(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Member> select(int firstItem, int lastItem) {
		// TODO Auto-generated method stub
		return real.select(firstItem, lastItem);
	}

	@Override
	public Member selectByEmailWithPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByIdWithEmail(int id, String email) {
		// TODO Auto-generated method stub
		
	}

}
