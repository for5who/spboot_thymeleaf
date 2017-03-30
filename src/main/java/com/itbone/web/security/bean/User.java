package com.itbone.web.security.bean;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Data
public class User implements UserDetails{

	private static final long serialVersionUID = -168179589369564169L;
	
	private String username;
	private String password;
	
	private List<Role> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	@Override
    public int hashCode() {
        return username.hashCode();
    }
    @Override
    public boolean equals(Object o) {
       if (o instanceof User) {
            return username.equals(((User) o).username);
       }
       return false;
    } 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

}
