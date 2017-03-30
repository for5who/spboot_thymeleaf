package com.itbone.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.itbone.web.security.service.CustomAuthenticationProvider;
import com.itbone.web.security.service.UserService;


@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource(name="userService")
	UserService userService;
	
	@Resource(name="customAuthenticationProvider")
	private CustomAuthenticationProvider customAuthenticationProvider;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/login").anonymous()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login_check")
        .failureUrl("/login?error")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/main", true)
        .and()
        .logout()
        .logoutSuccessUrl("/login?logout");
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().accessDeniedPage("/index");
        http.sessionManagement().invalidSessionUrl("/login");
      
   }

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(customAuthenticationProvider);
		  
		  
    }
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		   webSecurity.ignoring().antMatchers("/static/**"); 
	}


	
}
