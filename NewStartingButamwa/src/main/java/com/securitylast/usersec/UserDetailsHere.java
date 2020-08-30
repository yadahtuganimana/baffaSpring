package com.securitylast.usersec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securitylast.domain.User;
import com.securitylast.repository.UserRepository;

@Service
public class UserDetailsHere implements UserDetailsService {
	private UserRepository repository;

	public UserDetailsHere(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user found for ");
		}
		UserDetailsProp prop = new UserDetailsProp(user);
		return prop;
	}

}
