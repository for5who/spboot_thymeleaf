package com.itbone.web.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itbone.web.security.bean.User;
import com.itbone.web.security.mapper.SecurityMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	SecurityMapper securityMapper;
	
	@Override
	public User loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername username : " + username);
		
		//DB고객정보 조회
		User user = securityMapper.getUser(username);
		//log.debug("user getUsername: " + user.getUsername());
		//log.debug("user .getPassword : " +  user.getPassword());
		
		//임시
		//User user = new User();
		//user.setUsername("test");
		//user.setPassword("1234");
		
		return user;
	}


	public Collection<GrantedAuthority> getAuthorities(final String username) {
		log.info("getAuthorities username : " + username);
		
		//DB권한 조회
		//List<String> string_authorities = userMapper.readAuthority(username);
		List<String> string_authorities = Arrays.asList("lv1","blue","older");
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String authority : string_authorities) {
             authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;

	}

}
