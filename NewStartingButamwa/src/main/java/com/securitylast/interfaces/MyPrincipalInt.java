package com.securitylast.interfaces;

import java.security.Principal;

import com.securitylast.domain.User;

public interface MyPrincipalInt extends Principal {
	public User findbyFullnames(String user);
}
