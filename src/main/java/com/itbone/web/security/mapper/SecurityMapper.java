package com.itbone.web.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.itbone.web.security.bean.User;


@Mapper
public interface SecurityMapper {
	
	 public User getUser(String name);

}
