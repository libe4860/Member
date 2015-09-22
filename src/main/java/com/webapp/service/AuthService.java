package com.webapp.service;

import org.springframework.dao.EmptyResultDataAccessException;

import com.webapp.dao.MemberDao;
import com.webapp.exception.IdPasswordNotMatchException;
import com.webapp.model.AuthInfo;
import com.webapp.model.Member;
import com.webapp.util.Password;

public class AuthService {
	
	MemberDao dao;
	
	//설정파일에 injection처리 해줘야함.
	public void setMemberDao(MemberDao dao){
		this.dao = dao;
	}
	
	public AuthInfo authenticate(String email, String password){
		AuthInfo info = new AuthInfo();
//		info.setName("홍길동");
//		info.setEmail(email);
		try{
			Member m = dao.selectByEmail(email);
			//암호화해서 암호를 불러와 그 암호를 비교하는 로직 
			if(!m.getPassword().equals(Password.encode(password))){
				throw new IdPasswordNotMatchException();
			}
			//통과하면 AuthInfo를 불러옴 
			info.setEmail(m.getEmail());
			info.setName(m.getName());
			
		}catch (EmptyResultDataAccessException e){
			throw new IdPasswordNotMatchException(e);
		}
		
		return info;
	}
}
