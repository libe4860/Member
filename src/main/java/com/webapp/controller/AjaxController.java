package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	
	//ajax관련된 view만 받아들임
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String getView() {
		
		return "ajax/view";
	}

	//ajax페이지를 통해서 요청한 데이터를 읽어들임
	@RequestMapping(value="/array", method=RequestMethod.GET)
	public void getArray() {
		
	}
}
