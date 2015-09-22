package com.webapp.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.exception.MemberNotFoundException;
import com.webapp.model.Member;

//하나의 멤버에 대한 정보 조회
public class MemberInfoService {
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao dao){
		this.memberDao = dao;
	}
	
	//트랜잭션 적용
	//정상적으로 넘겨줄 때는 리턴, 단 없을 때 어떻게 처리할 것인가 여부
	@Transactional(readOnly=true)
	public Member getMember(int id){
		Member m = null;
		try{
			m = memberDao.selectById(id);
		}catch(EmptyResultDataAccessException e){
			throw new MemberNotFoundException(e);
		}
		return m;
	}
	
	//트랜잭션 적용
	//윗부분 getMember에 대한 오버로딩처리
	@Transactional(readOnly=true)
	public Member getMember(String email){
		Member m = null;
		try{
			m = memberDao.selectByEmail(email);
		}catch(EmptyResultDataAccessException e){
			throw new MemberNotFoundException(e);
		}
		return m;
	}

}
