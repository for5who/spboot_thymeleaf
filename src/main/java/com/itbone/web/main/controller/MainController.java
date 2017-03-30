package com.itbone.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@RequestMapping(value="/main")
	public ModelAndView main() {
		log.info("main Page Start!!");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","../main/main.jsp");
		mav.setViewName("layout/template");
		return mav;
	}
}