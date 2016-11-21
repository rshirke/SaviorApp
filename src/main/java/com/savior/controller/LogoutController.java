package com.savior.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

	private static ConnectionRepository connectionRepository;
	
	//
	// @Autowired
	// UserRepository repo;

	public LogoutController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String logout(HttpSession session) {
		System.out.println("Into logout method");
		session.invalidate();

		return "redirect:/connect/facebook";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String logoutget(HttpSession session) {

		System.out.println("Into logout method in get !!");

		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			System.out.println("The connection is already established !!!");
		}
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);

		connectionRepository.removeConnection(connection.getKey());

		return "redirect:/connect/facebook";
	}

}
