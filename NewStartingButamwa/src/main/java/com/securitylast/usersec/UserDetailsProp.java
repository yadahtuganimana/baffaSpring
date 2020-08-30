package com.securitylast.usersec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.securitylast.domain.User;

public class UserDetailsProp implements UserDetails {
   private User user;
   public   UserDetailsProp(User user){
	  this.user=user; 
   }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authority = new ArrayList<>();
		this.user.getRoleList().forEach(r ->{GrantedAuthority gr= new SimpleGrantedAuthority("ROLE_" + r);
		authority.add(gr);
		});
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPasswordd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}
	
	public String getUserfullname() {
		// TODO Auto-generated method stub
		return this.user.getUserfirstname();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.getActive().equals("ACTIVE");
	}

}
