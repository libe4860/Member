package com.webapp.util;

public class Pagination {
	final static int ITEMS_PER_PAGE = 10;
	final static int PAGES_PER_GROUP = 10;
	
	private int totalItem; 		 //select count(*) from city;
	private int pageNo;			 //요청페이지=current Page, Request Query Parameter
	private int itemsPerPage;
	private int pagesPerGroup;
	//유도타입
	// int totalPage
	// int firstItem
	// int lastItem
	//페이지 그룹핑
	// int firstPage
	// int lastPage
	
	// 생성할 때 초기화 한다.
	public Pagination() {
		this.totalItem = 0;
		this.pageNo = 1;
		this.itemsPerPage = ITEMS_PER_PAGE;
		this.pagesPerGroup = PAGES_PER_GROUP;
	}

	// 입력값에 의해서 초기화 한다.
	public Pagination(int itemsPerPage, int pagesPerGroup) {
		this(); //default생성자 호출
		
		//에러처리(default로 동작)
		if (itemsPerPage < 1)
			itemsPerPage = ITEMS_PER_PAGE;
		if (pagesPerGroup < 1)
			pagesPerGroup = PAGES_PER_GROUP;
		
		this.itemsPerPage = itemsPerPage;
		this.pagesPerGroup = pagesPerGroup;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		if (totalItem < 0)
			this.totalItem = 0;
		else
			this.totalItem = totalItem;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1)
			this.pageNo = 1;
		else
			this.pageNo = pageNo;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		if (itemsPerPage < 1)
			this.itemsPerPage = 1;
		else
			this.itemsPerPage = itemsPerPage;
	}

	public int getPagesPerGroup() {
		return pagesPerGroup;
	}

	public void setPagesPerGroup(int pagesPerGroup) {
		if (pagesPerGroup < 1)
			this.pagesPerGroup = 1;
		else
			this.pagesPerGroup = pagesPerGroup;
	}
	
	public int getTotalPage() {
		int totalPage = this.totalItem / this.itemsPerPage;
		if (this.totalItem % this.itemsPerPage > 0)
			totalPage++;
		
		return totalPage;
	}
	
	public int getFirstItem() {
		// 지정한 pageNo 이상일 때 firstItem이 보여지는 수보다 그 이상으로 보여지는 것을 수정함 
		int pageNo = this.pageNo;
		if (this.pageNo > getTotalPage())
			pageNo = getTotalPage();
			
		int firstItem = (pageNo-1) * this.itemsPerPage + 1;
		
		if (this.totalItem ==0)
			firstItem = 0;
		
		return firstItem;
	}
	
	public int getLastItem(){
		int lastItem = getFirstItem() + this.itemsPerPage -1;
		if (lastItem > this.totalItem)
			lastItem = this.totalItem;
		return lastItem;
	}
	
	public int getFirstPage() {
		int firstPage = (this.pageNo - 1) / this.pagesPerGroup * this.pagesPerGroup + 1;
		
		if (this.totalItem == 0)
			firstPage = 0;
			
		return firstPage;
	}
	
	public int getLastPage() {
		int lastPage = getFirstPage() + this.pagesPerGroup - 1 ;
		if (lastPage > getTotalPage())
			lastPage = getTotalPage();
		
		return lastPage;
	}
	
	// firstPage일 경우 이전 버튼 안보이게 하기(page==>1,2,3,4,5,6,7,8,9,10==>true) 
	public boolean isFirstGroup() {
		
		return getFirstPage() <= 1 ? true : false;
		
	}
	
	// lastPage일 경우 다음 버튼 안보이게 하기(page==>41,42,43,44,45 ==>true)
	public boolean isLastGroup() {
		
		if (getLastPage() == getTotalPage())
			return true;
		else
			return false;
		
	}
	
	
	
	
	
	
	
	
	
	
}
