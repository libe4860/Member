package com.webapp.model;

import java.util.List;

import com.webapp.util.Pagination;

public class MemberList extends Pagination{
	List<Member> members;
	
	public MemberList() {
		//default생성자 호출
		super();
	}//default생성자
	
	//한 페이지 그룹당 페이지를 달리 하고자 할 때
	public MemberList(int itemsPerPage, int pagesPerGroup){
		super(itemsPerPage, pagesPerGroup);
	}
	
	//getter
	public List<Member> getMembers() {
		return members;
	}

	//setter
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
}
