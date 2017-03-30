package com.itbone.web.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@PropertySource("classpath:global.properties")  //부트에서는 프로퍼티 연결을 어노테이션으로 하네여
public class SecurityController {
	
	@Value("${boot}") //원하는 단락을 가져와서 사용
    private String boot;
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request) {
		log.info("login Page Start!!"+boot);
		
		ModelAndView mav = new ModelAndView();
		
		//로그인 실패시 Exception Msg 가져오기, 로그인 실패시 다시 여기로 들어오니 엑셉션명 확인하고 뷰에 플래그 날려줘서 처리토록...
		//상수를 따로 모아놓았으면 좋겠는데 잘해본적이 없어서... 자신있는 부분이면 처리부탁드림...
		HttpSession session =(HttpSession)request.getSession();
		Exception springException = (Exception)session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if(springException!=null){
			mav.addObject("LOGIN_FAIL_FLAG",springException.getMessage());
		}
			
		mav.setViewName("security/login");
		
		return mav;
	}
	
	
}
