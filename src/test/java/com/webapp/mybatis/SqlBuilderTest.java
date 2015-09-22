package com.webapp.mybatis;

//SqlBuilder없애는 법 : import에 static넣어주고 맨 끝부분에 .*입력해준다.
import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class SqlBuilderTest {
	
	public static void main(String[] args) {
		//static클래스 즉 정적클래스로 되어있다.
		BEGIN(); //시작알림
		UPDATE("member");
if(true)	SET("email = ?");
if(false)	SET("password = ?");
if(true)	SET("name = ?");
if(false)	SET("regdate = ?");
if(true)WHERE("id = ? ");
if(true)WHERE("email = ?");
			OR();
if(true)WHERE("email = ?");
		
		System.out.println(SQL());
	}

}
