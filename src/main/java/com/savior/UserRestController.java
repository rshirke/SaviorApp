package com.savior;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired UserRepository repo;
	
	
	@RequestMapping("")
	public List<UserFB> listUsers() {
		
		return repo.findAll();
	}
	
	@RequestMapping("/{id}")
	public UserFB getById(@PathVariable Long id) {
		
		return repo.findOne(id);
	}
}
