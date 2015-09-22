package com.webapp.service;

import java.util.List;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.model.Member;
import com.webapp.model.MemberList;

//원칙은 인터페이스로 구현해야함, Dao에 의존적임
public class MemberListService {
	
	MemberDao dao;
	
	//setter
	public void setMemberDao(MemberDao dao){
		this.dao = dao;
	}
	
	@Transactional
	public List<Member> getListAll(){
		return dao.selectAll();
	}
	
	//인젝션받기
	PlatformTransactionManager tm;
	
	//어노테이션을 활용한 트랜잭션 시작
	@Transactional(isolation=Isolation.READ_COMMITTED,
				   propagation=Propagation.REQUIRED,
				   readOnly=true,
				   rollbackFor=Exception.class)
	//pageNo요청시 처리
	public MemberList getList(int pageNo){
		MemberList paging = new MemberList();

		//트랜잭션처리
		
		
		int totalItem = dao.selectTotalCount();
		//page number시 2가지 셋팅
		paging.setTotalItem(totalItem);
		paging.setPageNo(pageNo);
		
		//가져와야할 것
		int firstItem = paging.getFirstItem();
		int lastItem = paging.getLastItem();
		
		List<Member> members = dao.select(firstItem, lastItem);
		paging.setMembers(members);
		
		return paging;
	}
	

}
