package com.itbone.web.security.bean;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = -4844784660416577604L;
	
	private String name; //ROLE 명
	private List<Privilege> privileges; 
	 
	@Override
	public String getAuthority() {
		return this.name;
	}

}
