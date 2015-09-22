package com.webapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.command.LoginCommand;
import com.webapp.exception.IdPasswordNotMatchException;
import com.webapp.model.AuthInfo;
import com.webapp.service.AuthService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	static Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	AuthService service;
	
	//form제출
	@RequestMapping(method=RequestMethod.GET)
	public String loginForm(@ModelAttribute("login") LoginCommand command) {
		log.info("loginForm()...");
		//GET으로 들어올때만 Remember 체크함
		command.setRemember(true);
		return "login/loginForm";
	}
	
	//실제 DB에 처리
	@RequestMapping(method=RequestMethod.POST)
	public String login(@ModelAttribute("login")LoginCommand login, Errors errors, HttpSession session) {
		log.info("login()..." + login);
		
		/* validation */
		
		
		if(errors.hasErrors()){
			errors.reject("idPasswordNotMatch");
			return "login/loginForm";
		}
		
		/* login process */
		try{
			AuthInfo auth = service.authenticate(login.getEmail(), login.getPassword());
			
			session.setAttribute("auth", auth);
		}catch(IdPasswordNotMatchException ex){
			errors.reject("idPasswordNotMatch");
			return "login/loginForm";
			
		}
		return "redirect:/";
	}
}
