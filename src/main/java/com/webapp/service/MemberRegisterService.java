package com.webapp.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.dao.MemberDao;
import com.webapp.exception.AlreadyExistingMemberException;
import com.webapp.model.Member;

public class MemberRegisterService {
	
	MemberDao memberDao;
	
	public void setMemberDao(MemberDao dao){
		this.memberDao = dao;
	}
	
	@Transactional
	public void register(Member member){
		try{
			//성공시 1
			memberDao.insert(member);
//			memberDao.insertGenerator(member);
		}catch(DuplicateKeyException e){
			//실패시 throw처리
			throw new AlreadyExistingMemberException(e); //insert시 멤버중복에러처리
		}
	}

}

