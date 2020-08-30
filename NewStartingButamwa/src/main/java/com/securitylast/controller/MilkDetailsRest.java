package com.securitylast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.securitylast.domain.Selling;
import com.securitylast.domain.User;
import com.securitylast.interfaces.UserInt;

@RestController
@RequestMapping("/zirakamwa")
public class MilkDetailsRest {
    @Autowired
	UserInt userint;
    @RequestMapping(value = "/view",method = RequestMethod.GET)
	public  Iterable<Selling> amatayagurishijwe() {
		
		return userint.findAllSelling();
	}
   
    @RequestMapping(value = "/user",method = RequestMethod.GET)
	public  Iterable<User> getUsers() {
		
		return userint.findAllUSers();
	}
    @RequestMapping(value = "/savinga",method = RequestMethod.POST)
	public  User create(@RequestBody User user) {
		
		return userint.saveUSer(user);
	}
}
