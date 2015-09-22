package com.webapp.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

//등록이 되려면 scan이 인식해야함, 특정 어노테이션을 가지고 있는 클래스만 인식함!
//Controller를 줘야 request 맵핑이 같이 동작함!
@Controller
public class LocaleController {

	@Autowired
	LocaleResolver localeResolver;
	
	//사용자 맵핑처리
	@RequestMapping("/locale/{language:[a-z][a-z]}") //정규표현식:소문자 a부터 z까지 2자리만 허용가능함!
	public void setLocale(HttpServletRequest request, 
						  HttpServletResponse response, 
						  PrintWriter out, 
						  @PathVariable String language){
		
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		out.println("locale changed " + locale.getLanguage());
	}
}


