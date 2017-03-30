package com.itbone.web.security.service;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itbone.web.security.bean.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Resource(name="userService") 
    UserService userService;
	

	//private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("CustomAuthenticationProvider Start!!");
		
	    String username = authentication.getName(); //폼에서 입력받은 아이디
	    String password = (String) authentication.getCredentials(); //폼에서 입력받은 비번
	    
	    User user;
        Collection<? extends GrantedAuthority> authorities;
        
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        
        user = userService.loadUserByUsername(username);  //유저정보 
        authorities = user.getAuthorities(); //유저 권한
        
    	/*************************************
    	 * IP체크 기타 유효 체크 실패시 예외명 분기하여 처리
    	 ************************************/
        if(!password.equals(user.getPassword())){
        	log.info("E_DIFF_PWD");
        	throw new BadCredentialsException("E_DIFF_PWD");
        }
         
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	
}
