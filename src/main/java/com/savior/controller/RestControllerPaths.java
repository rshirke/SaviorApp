package com.savior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class RestControllerPaths {
	
//	@Autowired
//	UserRepository repo;
//	
	@RequestMapping("/helloinsert")
	public String hello()
	{
		//repo.save(new UserFB("sia", "sia@sia.com", "1234"));	
		return "/static/test.html";
	}
	
}
